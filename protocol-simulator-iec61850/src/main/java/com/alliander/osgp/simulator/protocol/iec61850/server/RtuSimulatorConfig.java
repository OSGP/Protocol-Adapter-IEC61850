/**
 * Copyright 2017 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package com.alliander.osgp.simulator.protocol.iec61850.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.openmuc.openiec61850.SclParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import com.alliander.osgp.simulator.protocol.iec61850.server.eventproducers.ServerSapEventProducer;

@Configuration
public class RtuSimulatorConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RtuSimulatorConfig.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ServerSapEventProducer serverSapEventProducer;

    @Bean
    public RtuSimulator rtuSimulator(@Value("${rtu.icd:Pampus_v0.4.5.icd}") final String icdFilename,
            @Value("${rtu.port:60102}") final Integer port,
            @Value("${rtu.serverName:WAGO61850Server}") final String serverName,
            @Value("${rtu.stopGeneratingValues:false}") final Boolean stopGeneratingValues,
            @Value("${rtu.updateValuesDelay:2000}") final Long updateValuesDelay,
            @Value("${rtu.updateValuesPeriod:10000}") final Long updateValuesPeriod) throws IOException {
        LOGGER.info(
                "Start simulator with icdFilename={}, port={}, serverName={}, stopGeneratingValues={}, updateValuesDelay={}, updateValuesPeriod={}",
                icdFilename, port, serverName, stopGeneratingValues, updateValuesDelay, updateValuesPeriod);
        // <<<<<<< HEAD
        // final InputStream icdFile =
        // this.resourceLoader.getResource("classpath:" +
        // icdFilename).getInputStream();
        // LOGGER.info("Simulator icdFile is {} on the classpath", icdFile !=
        // null ? "found" : "not found");
        //
        // try {
        // final RtuSimulator rtuSimulator = new RtuSimulator(port, icdFile,
        // serverName, this.serverSapEventProducer,
        // updateValuesDelay, updateValuesPeriod);
        // =======

        InputStream icdInputStream;
        final File icdFile = new File(icdFilename);
        if (icdFile.exists()) {
            LOGGER.info("Simulator icd {} found as external file", icdFilename);
            icdInputStream = this.resourceLoader.getResource("file:" + icdFilename).getInputStream();
        } else {
            LOGGER.info("Simulator icd {} not found as external file, load it from the classpath", icdFilename);
            icdInputStream = this.resourceLoader.getResource("classpath:" + icdFilename).getInputStream();
        }
        LOGGER.info("Simulator icd file loaded");

        try {
            final RtuSimulator rtuSimulator = new RtuSimulator(port, icdInputStream, serverName,
                    this.serverSapEventProducer, updateValuesDelay, updateValuesPeriod);
            if (stopGeneratingValues) {
                rtuSimulator.ensurePeriodicDataGenerationIsStopped();
            }
            rtuSimulator.start();
            return rtuSimulator;
        } catch (final SclParseException e) {
            LOGGER.warn("Error parsing SCL/ICD file {}", e);
        } finally {
            icdInputStream.close();
        }

        return null;
    }
}

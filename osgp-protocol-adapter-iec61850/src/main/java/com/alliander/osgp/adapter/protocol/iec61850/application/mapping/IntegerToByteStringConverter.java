package com.alliander.osgp.adapter.protocol.iec61850.application.mapping;
///**
// * Copyright 2015 Smart Society Services B.V.
// *
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// */
//package com.alliander.osgp.adapter.protocol.oslp.application.mapping;
//
//import ma.glasnost.orika.converter.BidirectionalConverter;
//import ma.glasnost.orika.metadata.Type;
//
//import com.alliander.osgp.oslp.OslpUtils;
//import com.google.protobuf.ByteString;
//
//public class IntegerToByteStringConverter extends BidirectionalConverter<Integer, ByteString> {
//
//    @Override
//    public Integer convertFrom(final ByteString source, final Type<Integer> destinationType) {
//        return OslpUtils.byteStringToInteger(source);
//    }
//
//    @Override
//    public ByteString convertTo(final Integer source, final Type<ByteString> destinationType) {
//        return OslpUtils.integerToByteString(source);
//    }
// }
package com.redis.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public final class XmlUtils {

    private XmlUtils() {

    }


    /**
     * 对象转xml
     * @param object
     * @return
     */
    public static String toXML(Object object) throws JAXBException {
        /**
         * 获取上下文对象
         */
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        /**
         * 根据上下文获取marshaller对象
         */
        Marshaller marshaller = context.createMarshaller();
        /**
         * 格式化XML输出，有分行和缩进
         */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\"?>");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(object, baos);
        String xmlObj = new String(baos.toByteArray());
        return xmlObj;
    }


    /**
     * 带有<?xml version="1.0"?>格式的xml解析成对象
     * @param inputStream
     * @param clazz
     * @param <T>
     * @return
     * @throws JAXBException
     */
    public static <T> T xmlToBean(InputStream inputStream, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(inputStream);
    }

}

package com.api.vetgroup.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <Origin, Destiny> Destiny parseObject(Origin origin, Class<Destiny> destination) {
        return mapper.map(origin, destination);
    }

    public static <Origin, Destiny> List<Destiny> parseListObjects(List<Origin> origin, Class<Destiny> destination) {
        List<Destiny> destinationObjects = new ArrayList<Destiny>();

        for (Origin o: origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        
        return destinationObjects;
    }
}

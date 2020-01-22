package bearmaps.proj2c;


import bearmaps.proj2c.server.handler.APIRouteHandlerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This code is using BearMaps skeleton code version 4.0.
 * @author Alan Yao, Josh Hug
 */
public class MapServer {


    /**
     * This is where the MapServer is started.
     * @param args
     */
    public static void main(String[] args) {

        MapServerInitializer.initializeServer(APIRouteHandlerFactory.handlerMap);

        List<Integer> list = new ArrayList<>();
        list.add(0, 1);
        for (int i = 0; i < 3; i++) {
            //第一位一定是1
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
        }
    }

}

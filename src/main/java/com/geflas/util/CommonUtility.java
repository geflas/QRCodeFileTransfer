package com.geflas.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class CommonUtility {

    public static String getInetAddress(){
        String iNetAddress = "no_address_available";
        List<InetAddress> broadcastList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                if (networkInterface.isLoopback()){// || !networkInterface.isUp()) {
                    continue;
                }

                networkInterface.getInterfaceAddresses().stream()
                        .map(InterfaceAddress::getAddress)
                        .filter(Objects::nonNull)
                        .forEach(broadcastList::add);



            }
        }catch (Throwable e){
            System.err.println(e.getMessage());
        }
        if(!broadcastList.isEmpty()) {
            System.out.println("Size :"+broadcastList.size()+",List:"+broadcastList);
            iNetAddress = broadcastList.get(broadcastList.size()-1).toString();
        }
        return iNetAddress;
    }
    public static void writeIntoHardDisk(String path, String filename, byte[] data){
        path = path ==null ? System.getProperty("java.io.tmpdir"): path;
        FileOutputStream fout;
        BufferedOutputStream bout;
        try {
            fout = new FileOutputStream(path+ FileSystems.getDefault().getSeparator() +filename);
            bout = new BufferedOutputStream(fout);
            bout.write(data);bout.close();
            fout.close();
        }catch (IOException ioe){
            System.out.println("IOException :"+ioe.getMessage());
        }

    }

}

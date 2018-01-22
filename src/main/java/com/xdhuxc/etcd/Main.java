package com.xdhuxc.etcd;

import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.kv.DeleteResponse;
import com.coreos.jetcd.kv.GetResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        //创建客户端
        Client client = Client.builder().endpoints("http://192.168.244.128:2379").build();
        KV kvClient = client.getKVClient();
        ByteSequence key = ByteSequence.fromString("xdhuxc");
        ByteSequence value = ByteSequence.fromString("adobe1");

        //写入键值对
        //kvClient.put(key, value);

        //根据键获取值
        CompletableFuture<GetResponse> completableFuture = kvClient.get(key);

        try {
            GetResponse response = completableFuture.get();
            System.out.println(response.toString());

            System.out.println("------------------------------------");

            System.out.println(response.getKvs());

            //根据键删除值
            DeleteResponse deleteResponse = kvClient.delete(key).get();
            System.out.println(deleteResponse.getDeleted());

            System.out.println("------------------------------------");
            response = completableFuture.get();
            System.out.println(response.toString());

            System.out.println("------------------------------------");

            System.out.println(response.getKvs());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

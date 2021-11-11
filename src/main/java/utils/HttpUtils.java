package utils;
import com.google.gson.Gson;
import dtos.*;
import org.glassfish.jersey.internal.guava.Ticker;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUtils {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        fetchDataParallel();
    }

    private static Gson gson = new Gson();
    public static TickerDTO fetchDataParallel() throws IOException, MalformedURLException, ExecutionException, InterruptedException
    {  ExecutorService es = Executors.newCachedThreadPool();
        Future<TickerDTO> bitcoinFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=BTC-USD"), TickerDTO.class));
        Future<TickerDTO> ethereumFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=ETH-USD"), TickerDTO.class));
        Future<TickerDTO> dogeFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=DOGE-BTC"), TickerDTO.class));
        Future<TickerDTO> litecoinFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=LTC-BTC"), TickerDTO.class));
        Future<TickerDTO> rippleFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=XRP-BTC"), TickerDTO.class));


        TickerDTO combinedTicker = new TickerDTO(new ArrayList<CrypDTO>());

        combinedTicker.addTicker(bitcoinFuture.get().getTickers().get(0));
        combinedTicker.addTicker(ethereumFuture.get().getTickers().get(0));
        combinedTicker.addTicker(dogeFuture.get().getTickers().get(0));
        combinedTicker.addTicker(litecoinFuture.get().getTickers().get(0));
        combinedTicker.addTicker(rippleFuture.get().getTickers().get(0));

        String combined = gson.toJson(combinedTicker);

        System.out.println(combined);
        return combinedTicker;
    }

    public static String fetchData(String _url) throws MalformedURLException, IOException{
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");con.setRequestMethod("GET");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        return jsonStr;
    }
    }


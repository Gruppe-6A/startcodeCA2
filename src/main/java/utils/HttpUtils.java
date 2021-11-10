package utils;
import com.google.gson.Gson;
import com.nimbusds.jose.shaded.json.JSONArray;
import dtos.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    public static CryptoDTO fetchDataParallel() throws IOException, MalformedURLException, ExecutionException, InterruptedException
    {  ExecutorService es = Executors.newCachedThreadPool();
        Future<BitcoinDTO> bitcoinDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=BTC-USD").substring(11, 79), BitcoinDTO.class));
        Future<EthereumDTO> ethereumDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=ETH-USD"), EthereumDTO.class));
        Future<DogeDTO> dogeDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=DOGE-BTC"), DogeDTO.class));
        Future<LitecoinDTO> litecoinDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=LTC-BTC"), LitecoinDTO.class));
        Future<RippleDTO> rippleDTOFuture = es.submit(
                () -> gson.fromJson(HttpUtils.fetchData("https://api.coinstats.app/public/v1/tickers?exchange=yobit&pair=XRP-BTC"), RippleDTO.class));
        BitcoinDTO bitcoinDTO = bitcoinDTOFuture.get();

        System.out.println(bitcoinDTO.getprice());
        System.out.println(bitcoinDTOFuture.isDone());

        EthereumDTO ethereumDTO = ethereumDTOFuture.get();
        DogeDTO dogeDTO = dogeDTOFuture.get();
        LitecoinDTO litecoinDTO = litecoinDTOFuture.get();
        RippleDTO rippleDTO = rippleDTOFuture.get();

        CryptoDTO cryptoDTO = new CryptoDTO(bitcoinDTO, ethereumDTO, dogeDTO, litecoinDTO, rippleDTO);
        String combined = gson.toJson(cryptoDTO);

        System.out.println(combined);
        return cryptoDTO;
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


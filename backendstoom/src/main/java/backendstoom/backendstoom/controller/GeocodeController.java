package backendstoom.backendstoom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backendstoom.backendstoom.domain.GeocodeResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class GeocodeController {
	
	@RequestMapping(path = "/geocode", method = RequestMethod.GET )
	public GeocodeResult getGeocode(@RequestParam String address) throws IOException {
       OkHttpClient client = new OkHttpClient();
       String encodedAddress = URLEncoder.encode(address, "UTF-8");
    
       Request request = new Request.Builder()
    		   .url("https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedAddress)
               .get()
               .addHeader("address", encodedAddress)
               .addHeader("key", "AIzaSyBm3xh9oZP1ksMWcMzVaZQevWlrtb8tIgc")
               .build();
       
       ResponseBody responseBody = client.newCall(request).execute().body();
       
       ObjectMapper objectMapper = new ObjectMapper();
       
       GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
       
       return result;
   }
}

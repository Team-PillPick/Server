package com.kbsc.pillpick.service;

import com.kbsc.pillpick.common.response.BasicResponse;
import com.kbsc.pillpick.domain.Box;
import com.kbsc.pillpick.domain.Medicine;
import com.kbsc.pillpick.dto.boxDto.BoxResponseDto;
import com.kbsc.pillpick.dto.medicineDto.GetMedicineResponseDto;
import com.kbsc.pillpick.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BoxService {

    private final BoxRepository boxRepository;
    public void saveBox() throws IOException, ParseException {
        if (boxRepository.findAll().isEmpty()) {
            for (List<String> boxes : readCSV()) {

                Map<String, String> coordinate = getCoordinate(boxes.get(2));
                if(!coordinate.isEmpty()){

                    Box box = new Box(boxes.get(1).replaceAll("\\\"",""), boxes.get(2).replaceAll("\\\"",""), Float.valueOf(coordinate.get("x")), Float.valueOf(coordinate.get("y")));

                    boxRepository.save(box);
                }
            }
        }

    }

    public List<List<String>> readCSV() {
        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File("/Users/yunsangjin/R/my_boxes.csv");
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            Charset.forName("UTF-8");
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }

    public Map<String, String> getCoordinate(String address) throws IOException, ParseException {
        Map<String, String> result = new HashMap<>();
        String GEOCODE_USER_INFO = "d57625f6c9498ce6a132ce33adc6e1f6";

        //주소안에 띄어쓰기때문에 400에러가 나는것을 해결

        address = URLEncoder.encode(address, "UTF-8");

        String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;

        String jsonString = new String();

        String buf;

        URL Url = new URL(url);

        HttpsURLConnection conn = (HttpsURLConnection) Url.openConnection();
        String auth = "KakaoAK " + GEOCODE_USER_INFO;
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Requested-With", "curl");
        conn.setRequestProperty("Authorization", auth);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        while ((buf = br.readLine()) != null) {
            jsonString += buf;
        }
        JSONParser paser = new JSONParser();

        JSONObject J = (JSONObject) paser.parse(jsonString);
        JSONObject meta = (JSONObject) J.get("meta");

        JSONArray data = (JSONArray) J.get("documents");
        long size = (long) meta.get("total_count");



        if (size > 0) {
            JSONObject jsonX = (JSONObject) data.get(0);
            result.put("x", jsonX.get("x").toString());
            result.put("y", jsonX.get("y").toString());
        }

        return result;
    }

    public ResponseEntity<BasicResponse> readAllBoxes() {
        BasicResponse basicResponse = new BasicResponse();
        HttpStatus httpStatus = null;

        List<Box> boxList = boxRepository.findAll();

        List<Object> dataList = new ArrayList<>();

        for (Box box : boxList) {
            dataList.add(BoxResponseDto.builder()
                    .id(box.getId())
                    .name(box.getName())
                    .address(box.getAddress())
                    .x(box.getCoordinateX())
                    .y(box.getCoordinateY())
                    .build());
        }
        httpStatus = HttpStatus.OK;
        basicResponse = BasicResponse.builder()
                .status(HttpStatus.OK.value())
                .message("모든 수거함 조회 성공")
                .data(dataList)
                .success(true)
                .build();

        return new ResponseEntity<>(basicResponse, httpStatus);
    }
}

package com.work2022.interview.coindesk.RestService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.work2022.interview.coindesk.Repository.CoindeskBPIRepository;
import com.work2022.interview.coindesk.Repository.CoindeskJsonRepository;
import com.work2022.interview.coindesk.dao.NewAPIInfoRepository;
import com.work2022.interview.coindesk.model.Coindesk;
import com.work2022.interview.coindesk.model.CoindeskBPI;
import com.work2022.interview.coindesk.model.CoindeskJson;
import com.work2022.interview.coindesk.model.NewAPIInfo;
import com.work2022.interview.coindesk.utils.adapter.LocalDateAdapter;
import com.work2022.interview.coindesk.utils.adapter.LocalDateTimeAdapter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoindeskRestService {

//	private static final Logger log = LoggerFactory.getLogger(CoindeskRestService.class);
//	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private CoindeskJsonRepository repository;
	
	@Autowired
	private CoindeskBPIRepository coindeskBPIRepository;
	
	@Autowired
	private NewAPIInfoRepository newAPIInfoRepository;

	public String getAPI() {
		RestTemplate rTemplate = new RestTemplate();

//		log.info("get class from non static");
		log.info("get class from non static");

		String json = rTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);

//		CoindeskJson coindesk = new CoindeskJson();
//		coindesk.setJson(json);
//		
//		repository.save(coindesk);

		return json;
	}

	public List<NewAPIInfo> getNewAPIInfo() {
		String apiValue = getAPI();
		
		CoindeskJson apiCoindeskJson = new CoindeskJson();
		apiCoindeskJson.setJson(apiValue);
		
//		repository.save(apiCoindesk);
		repository.saveAndFlush(apiCoindeskJson);
		log.info("api: " + apiValue);
		
		
		
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter(DateTimeFormatter.ISO_DATE))
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter(DateTimeFormatter.ISO_DATE_TIME))
				.setPrettyPrinting().create();

		Coindesk coindesk = gson.fromJson(apiValue, new TypeToken<Coindesk>(){}.getType());


		
//		// time
//		CoindeskTime coindeskTime = coindesk.getTime();
//
//		// disclaimer
//		String disclaimer = coindesk.getDisclaimer();
//
//		// chartName
//		String chartName = coindesk.getChartName();

		// bpi(s)
		Map<String, CoindeskBPI> mapBPI = coindesk.getBpi();
		for (CoindeskBPI bpi : mapBPI.values()) {
			bpi.setJosn(apiCoindeskJson);

			System.out.println("bpi = " + bpi);
			coindeskBPIRepository.saveAndFlush(bpi);
		}
		


		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		String updateTime = coindesk.getTime().getUpdatedISO().format(formatter);
		List<NewAPIInfo> infoList = newAPIInfoRepository.getNewAPI();
		
		if (infoList.size() > 0) {
			for (NewAPIInfo info : infoList) {
				info.setUpdated(updateTime);
			}
			
//			NewAPIInfo info = infoList.get(0);
//			info.setUpdated(updateTime);
//			
//			infoList.set(0, info);
		}


		return infoList;
	}

}

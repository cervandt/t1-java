package com.mediamath.terminalone.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Campaign implements T1Entity {
	
	private static final String entityName = "Campaign";

	public enum conv {
		every, one, variable
	};

	public enum freq_ints {
		hour, day, week, month, not_applicable
	}; // should be not-applicable

	public enum freq_types {
		even, asap, no_limit
	}; // should be no-limit

	public enum goal_cats {
		audience, engagement, response, none
	};

	public enum goal_types {
		spend, reach, cpc, cpe, cpa, roi, none
	};

	public enum serv_types {
		SELF, MANAGED
	};

	private ArrayList<T1Cost> ad_server_fee = new ArrayList<T1Cost>();
	private int ad_server_id;
	private String ad_server_password;
	private String ad_server_username;
	private int advertiser_id;
	private float agency_fee_pct;
	private String conversion_type;
	private int conversion_variable_minutes;
	private Date created_on;
	private String currency_code;
	private boolean dcs_data_is_campaign_level;
	private Date end_date;
	private int frequency_amount;
	private freq_ints frequency_interval;
	private freq_types frequency_type;
	private float goal_alert;
	private goal_cats goal_category;
	private goal_types goal_type;
	private ArrayList<T1Cost> goal_value = new ArrayList<T1Cost>();
	private boolean has_custom_attribution;
	private int id;
	private String io_name;
	private String io_reference_num;
	private Date initial_start_date;
	private float margin_pct;
	private int merit_pixel_id;
	private String name;
	private int pc_window_minutes;
	private float pv_pct;
	private int pv_window_minutes;
	private serv_types service_type;
	private ArrayList<T1Cost> spend_cap_amount = new ArrayList<T1Cost>();
	private boolean spend_cap_automatic;
	private boolean spend_cap_enabled;
	private Date start_date;
	private boolean status;
	private ArrayList<T1Cost> total_budget = new ArrayList<T1Cost>();
	private Date updated_on;
	private boolean use_default_ad_server;
	private boolean use_mm_freq;
	private int version;
	private String zone_name;
	private Map<Date, Double> margins = new HashMap<Date, Double>();
	
	private Advertiser advertiser;
	private AdServer ad_server;
	private Pixel merit_pixel;
	
	public void setMargins(Date date, Double doubleval) {
		margins.put(date, new BigDecimal(doubleval).setScale(4, RoundingMode.HALF_EVEN).doubleValue());
	}
	
	public void resetMargins(){
		margins.clear();
	}
	
	public Map<Date, Double> getMargins() {
		return this.margins;
	}

	public int getAd_server_id() {
		return ad_server_id;
	}

	public void setAd_server_id(int ad_server_id) {
		this.ad_server_id = ad_server_id;
	}

	public String getAd_server_password() {
		return ad_server_password;
	}

	public void setAd_server_password(String ad_server_password) {
		this.ad_server_password = ad_server_password;
	}

	public String getAd_server_username() {
		return ad_server_username;
	}

	public void setAd_server_username(String ad_server_username) {
		this.ad_server_username = ad_server_username;
	}

	public int getAdvertiser_id() {
		return advertiser_id;
	}

	public void setAdvertiser_id(int advertiser_id) {
		this.advertiser_id = advertiser_id;
	}

	public float getAgency_fee_pct() {
		return agency_fee_pct;
	}

	public void setAgency_fee_pct(float agency_fee_pct) {
		this.agency_fee_pct = agency_fee_pct;
	}

	public String getConversion_type() {
		return conversion_type;
	}

	public void setConversion_type(String conversion_type) {
		this.conversion_type = conversion_type;
	}

	public int getConversion_variable_minutes() {
		return conversion_variable_minutes;
	}

	public void setConversion_variable_minutes(int conversion_variable_minutes) {
		this.conversion_variable_minutes = conversion_variable_minutes;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public boolean isDcs_data_is_campaign_level() {
		return dcs_data_is_campaign_level;
	}

	public void setDcs_data_is_campaign_level(boolean dcs_data_is_campaign_level) {
		this.dcs_data_is_campaign_level = dcs_data_is_campaign_level;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getFrequency_amount() {
		return frequency_amount;
	}

	public void setFrequency_amount(int frequency_amount) {
		this.frequency_amount = frequency_amount;
	}

	public freq_ints getFrequency_interval() {
		return frequency_interval;
	}

	public void setFrequency_interval(freq_ints frequency_interval) {
		this.frequency_interval = frequency_interval;
	}

	public freq_types getFrequency_type() {
		return frequency_type;
	}

	public void setFrequency_type(freq_types frequency_type) {
		this.frequency_type = frequency_type;
	}

	public float getGoal_alert() {
		return goal_alert;
	}

	public void setGoal_alert(float goal_alert) {
		this.goal_alert = goal_alert;
	}

	public goal_cats getGoal_category() {
		return goal_category;
	}

	public void setGoal_category(goal_cats goal_category) {
		this.goal_category = goal_category;
	}

	public goal_types getGoal_type() {
		return goal_type;
	}

	public void setGoal_type(goal_types goal_type) {
		this.goal_type = goal_type;
	}

	public boolean isHas_custom_attribution() {
		return has_custom_attribution;
	}

	public void setHas_custom_attribution(boolean has_custom_attribution) {
		this.has_custom_attribution = has_custom_attribution;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIo_name() {
		return io_name;
	}

	public void setIo_name(String io_name) {
		this.io_name = io_name;
	}

	public String getIo_reference_num() {
		return io_reference_num;
	}

	public void setIo_reference_num(String io_reference_num) {
		this.io_reference_num = io_reference_num;
	}

	public Date getInitial_start_date() {
		return initial_start_date;
	}

	public void setInitial_start_date(Date initial_start_date) {
		this.initial_start_date = initial_start_date;
	}

	public float getMargin_pct() {
		return margin_pct;
	}

	public void setMargin_pct(float margin_pct) {
		this.margin_pct = margin_pct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPc_window_minutes() {
		return pc_window_minutes;
	}

	public void setPc_window_minutes(int pc_window_minutes) {
		this.pc_window_minutes = pc_window_minutes;
	}

	public float getPv_pct() {
		return pv_pct;
	}

	public void setPv_pct(float pv_pct) {
		this.pv_pct = pv_pct;
	}

	public int getPv_window_minutes() {
		return pv_window_minutes;
	}

	public void setPv_window_minutes(int pv_window_minutes) {
		this.pv_window_minutes = pv_window_minutes;
	}

	public serv_types getService_type() {
		return service_type;
	}

	public void setService_type(serv_types service_type) {
		this.service_type = service_type;
	}

	public boolean isSpend_cap_automatic() {
		return spend_cap_automatic;
	}

	public void setSpend_cap_automatic(boolean spend_cap_automatic) {
		this.spend_cap_automatic = spend_cap_automatic;
	}

	public boolean isSpend_cap_enabled() {
		return spend_cap_enabled;
	}

	public void setSpend_cap_enabled(boolean spend_cap_enabled) {
		this.spend_cap_enabled = spend_cap_enabled;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	public boolean isUse_default_ad_server() {
		return use_default_ad_server;
	}

	public void setUse_default_ad_server(boolean use_default_ad_server) {
		this.use_default_ad_server = use_default_ad_server;
	}

	public boolean isUse_mm_freq() {
		return use_mm_freq;
	}

	public void setUse_mm_freq(boolean use_mm_freq) {
		this.use_mm_freq = use_mm_freq;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

	public String getEntityname() {
		return entityName;
	}

	public int getMerit_pixel_id() {
		return merit_pixel_id;
	}

	public void setMerit_pixel_id(int merit_pixel_id) {
		this.merit_pixel_id = merit_pixel_id;
	}

	public ArrayList<T1Cost> getTotal_budget() {
		return total_budget;
	}
	
	

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public AdServer getAd_server() {
		return ad_server;
	}

	public void setAd_server(AdServer ad_server) {
		this.ad_server = ad_server;
	}
	

	public Pixel getMerit_pixel() {
		return merit_pixel;
	}

	public void setMerit_pixel(Pixel merit_pixel) {
		this.merit_pixel = merit_pixel;
	}

	public void setTotal_budget(double value, String currency_code) {
		this.total_budget.clear();
		if(value > 0) {
			T1Cost cost = new T1Cost();
			cost.setValue(value);
			
			if(currency_code != null && !currency_code.isEmpty()) {
				cost.setCurrency_code(currency_code);
			}
		
			this.total_budget.add(cost);
		} 
	}

	public ArrayList<T1Cost> getAd_server_fee() {
		return ad_server_fee;
	}

	public void setAd_server_fee(double value, String currency_code) {
		this.ad_server_fee.clear();
		if(value > 0) {
			T1Cost cost = new T1Cost();
			cost.setValue(value);
			
			if(currency_code != null && !currency_code.isEmpty()) {
				cost.setCurrency_code(currency_code);
			}
		
			this.ad_server_fee.add(cost);
		} 
	}

	public ArrayList<T1Cost> getSpend_cap_amount() {
		return spend_cap_amount;
	}

	public void setSpend_cap_amount(double value, String currency_code) {
		this.spend_cap_amount.clear();
		if(value > 0) {
			T1Cost cost = new T1Cost();
			cost.setValue(value);
			
			if(currency_code != null && !currency_code.isEmpty()) {
				cost.setCurrency_code(currency_code);
			}
		
			this.spend_cap_amount.add(cost);
		} 
	}

	public ArrayList<T1Cost> getGoal_value() {
		return goal_value;
	}

	public void setGoal_value(double value, String currency_code) {
		this.goal_value.clear();
		if(value > 0) {
			T1Cost cost = new T1Cost();
			cost.setValue(value);
			
			if(currency_code != null && !currency_code.isEmpty()) {
				cost.setCurrency_code(currency_code);
			}
		
			this.goal_value.add(cost);
		} 
	}



}

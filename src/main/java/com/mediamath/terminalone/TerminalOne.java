package com.mediamath.terminalone;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.ws.rs.core.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.Exceptions.ClientException;
import com.mediamath.terminalone.Exceptions.ParseException;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Pixel;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategySupplySource;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.T1Error;
import com.mediamath.terminalone.models.T1Response;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.ThreePASCreativeBatchApprove;
import com.mediamath.terminalone.models.ThreePASCreativeUpload;
import com.mediamath.terminalone.service.GetService;
import com.mediamath.terminalone.service.PostService;
import com.mediamath.terminalone.service.T1Service;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.T1JsonToObjParser;

/**
 * handles the authentication, session, entity
 * retrieval, creation etc.
 *
 */
public class TerminalOne {

	/*
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(TerminalOne.class);

	/*
	 * connection object
	 */
	public Connection connection = null;

	/*
	 * service object.
	 */
	private T1Service jt1Service = null;
	
	private PostService postService = null;
	
	private GetService getService = null;
	
	
	/*
	 * maintains user session
	 */
	//private HashMap<String, HashMap<String, String>> user = new HashMap<String, HashMap<String, String>>();
	
	private T1Response user = null;

	/*
	 * is authenticated? 
	 */
	private boolean authenticated = false;

	
	/**
	 * Default Constructor
	 */
	public TerminalOne() {
		logger.info("Loading Environment - setting up connection.");
		connection = new Connection();
		jt1Service = new T1Service();
	}

	/**
	 * the other constructor, tries to connect with the credentials provided.
	 * @throws ClientException 
	 * 
	 */
	public TerminalOne(String username, String password, String api_key) throws ClientException {
		this();
		/*	logger.info("Loading Environment - setting up connection.");
		connection = new Connection();
		jt1Service = new T1Service();*/
		
		validateLoginCredentials(username, password, api_key);

		logger.info("Loading Environment - Authenticating.");
		Form form = jt1Service.getLoginFormData(username, password, api_key);

		logger.info("Loading Environment - Authenticating.");
		String url = jt1Service.constructURL(new StringBuffer("login"));
		String response = connection.post(url, form, null);
		getUserSessionInfo(response);
		postService = new PostService(connection, user);
		getService  = new GetService();
		
		if(this.getUser() != null && this.getUser().getData() != null) {
			if(this.getUser().getData().getSession() != null && this.getUser().getData().getSession().getSessionid() != null 
					&& !this.getUser().getData().getSession().getSessionid().isEmpty()) {
				this.authenticated = true;
			}
		}
		
	}

	/**
	 * @param username
	 * @param password
	 * @param api_key
	 * @throws ClientException
	 */
	private void validateLoginCredentials(String username, String password, String api_key) throws ClientException {
		if(api_key == null || api_key.isEmpty()) {
			logger.error("Environment does not exist");
			throw new ClientException("Please Provide Valid Enviornment");
		}
		
		if(username.isEmpty() || password.isEmpty()) {
			logger.error("Please provide valid credentials.");
			throw new ClientException("Please Provide Valid Username and Password.");
		}
	}
	
	/**
	 * basic authentication method.
	 * 
	 * @return boolean isauthenticated.
	 */
	public boolean authenticate(String username, String password, String api_key) throws ClientException {

		// TODO validate
		logger.info("Authenticating.");
		validateLoginCredentials(username, password, api_key);
		
		Form form = jt1Service.getLoginFormData(username, password, api_key);
		String url = jt1Service.constructURL(new StringBuffer("login"));
		String response = connection.post(url, form, null);
		
		getUserSessionInfo(response);
		
		postService = new PostService(connection, user);
		getService  = new GetService();
		
		if(this.getUser() != null && this.getUser().getData() != null) {
			if(this.getUser().getData().getSession() != null && this.getUser().getData().getSession().getSessionid() != null 
					&& !this.getUser().getData().getSession().getSessionid().isEmpty()) {
				this.authenticated = true;
			}
		}
		
		return isAuthenticated();
	}

	/**
	 * Maintains user session information.
	 * 
	 * @param response
	 */
	private void getUserSessionInfo(String response) {
		Gson gson = new Gson();
		T1Response resp = null;
		
		Type responseTypeInfo = new TypeToken<T1Response>() {}.getType();
		resp = gson.fromJson(response, responseTypeInfo);
		this.setUser(resp);
		
	}
	
	
	/**
	 * saves the given Agency.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Agency save(Agency entity) throws ClientException, ParseException {
		Agency agency = null;
		if(isAuthenticated()) {
			agency = postService.save(entity);
		}
		return agency;
	}
	
	/**
	 * saves the given Advertiser.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Advertiser save(Advertiser entity) throws ClientException, ParseException {
		Advertiser advertiser = null;
		if(isAuthenticated()) {
			advertiser = postService.save(entity);
		}
		return advertiser;
	}
	
	
	/**
	 * saves the given Strategy.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Strategy save(Strategy entity) throws ClientException, ParseException {
		Strategy strategy = null;
		if(isAuthenticated()) {
			strategy = postService.save(entity);
		}
		return strategy;
	}
	
	/**
	 * saves the given Strategy Concepts.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public StrategyConcept save(StrategyConcept entity) throws ClientException, ParseException {
		StrategyConcept strategyConcept = null;
		if(isAuthenticated()) {
			strategyConcept = postService.save(entity);
		}
		return strategyConcept;
	}
	
	/**
	 * saves the given Strategy Supply Sources.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public StrategySupplySource save(StrategySupplySource entity) throws ClientException, ParseException {
		StrategySupplySource strategySupplySource = null;
		if(isAuthenticated()) {
			strategySupplySource = postService.save(entity); 
		}
		return strategySupplySource;
	}
	
	/**
	 * saves the given Organization.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Organization save(Organization entity) throws ClientException, ParseException {
		Organization organization = null;
		if(isAuthenticated()) {
			organization = postService.save(entity);
		}
		return organization;
	}
	
	/**
	 * saves the given Pixel.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Pixel save(Pixel entity) throws ClientException, ParseException {
		Pixel pixel = null;
		if(isAuthenticated()) {
			pixel = postService.save(entity); 
		}
		return pixel;
	}
	
	/**
	 * saves the given campaign.
	 * 
	 * @param entity
	 * @return
	 * @throws ParseException
	 * @throws ClientException
	 */
	public Campaign save(Campaign entity) throws ParseException, ClientException {
		Campaign campaign = null;
		if(isAuthenticated()) {
			campaign = postService.save(entity); 
		}
		return campaign;
	}
	
	/**
	 * saves concepts
	 * 
	 * @param entity
	 * @return
	 * @throws ParseException
	 * @throws ClientException
	 */
	public Concept save(Concept entity) throws ParseException, ClientException {
		Concept concept = null;
		if(isAuthenticated()) {
			concept = postService.save(entity); 
		}
		return concept;
	}
	
	/**
	 * saves Atomic Creative
	 * 
	 * @param entity
	 * @return
	 * @throws ParseException
	 * @throws ClientException
	 */
	public AtomicCreative save(AtomicCreative entity) throws ParseException, ClientException {
		AtomicCreative atomicCreative = null;
		if(isAuthenticated()) {
			atomicCreative = postService.save(entity); 
		}
		return atomicCreative;
	}
	
	public ThreePASCreativeUpload save3pasCreativeUpload(String filePath, String fileName, String name) throws ClientException, IOException {
		ThreePASCreativeUpload response = null;
		if(isAuthenticated()) {
			response = postService.save3pasCreativeUpload(filePath, fileName, name);
		}
		return response;
	}
	

	//TODO return type
	public void save3pasCreativeUploadBatch(ThreePASCreativeBatchApprove batchApprove) throws ClientException, IOException {
		if(isAuthenticated()) {
			postService.save3pasCreativeUploadBatch(batchApprove);
		}
	}
	
	public TOneASCreativeAssetsUpload saveT1ASCreativeAssetsUpload(String filePath, String fileName, String name) throws ClientException, IOException {
		TOneASCreativeAssetsUpload response = null;
		if(isAuthenticated()) {
			response = postService.saveT1asCreativeAssets(filePath, fileName, name);
		}
		return response;
	}
	
	
	public JsonResponse<? extends T1Entity> saveTOneASCreativeAssetsApprove(TOneASCreativeAssetsApprove creativeAssetsApprove) throws ClientException {
		JsonResponse<? extends T1Entity> response = null;
		if(isAuthenticated()) {
			response = postService.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
		}
		return response;
	}
	
	/**
	 * Get.
	 * 
	 * @param query
	 * @return
	 * @throws ClientException
	 * @throws ParseException
	 */
	public JsonResponse<? extends T1Entity> get(QueryCriteria query) throws ClientException, ParseException {
		
		StringBuffer path= getService.get(query);
		
		// get the data from t1 servers.
		String finalPath = jt1Service.constructURL(path);
		String response = this.connection.get(finalPath, this.getUser());
		JsonResponse<? extends T1Entity> jsonResponse;
		// parse the data to entities.
		try{
			jsonResponse = parseResponse(query, response);
			jsonResponse = checkResponseEntities(jsonResponse);
		
		} catch (ParseException e) {
			
			throw new ClientException("Unable to parse the response");
		
		}
		
		// filter and validate data
		
		
		
		return jsonResponse;
	}
	
	private JsonResponse<? extends T1Entity> checkResponseEntities(JsonResponse<? extends T1Entity> jsonResponse) throws ClientException {
		
		if(jsonResponse != null) {
			StringBuffer strbuff = null;
			
			if(jsonResponse.getErrors() != null) {
				
				for(T1Error error: jsonResponse.getErrors()) {
					if(error.getMessage() != null) {
						if(strbuff == null){ 
							strbuff = new StringBuffer(error.getMessage());
						} else {
							strbuff.append(", " + error.getMessage());
						}
					}
				}
				// throw the error to client
				throw new ClientException(strbuff.toString());
			}
		}
		// else return the object
		return jsonResponse;
	}


/**
 * parses the response to objects.
 * 
 * @param query
 * @param response
 * @return
 * @throws ParseException
 */
private JsonResponse<? extends T1Entity> parseResponse(QueryCriteria query, String response) throws ParseException {
	T1JsonToObjParser parser = new T1JsonToObjParser();
	int result = parser.getJsonElementType(response);
	Type JsonResponseType = null;
	JsonResponse<? extends T1Entity> jsonresponse = null;
	
	if(query.collection != null) {
		
		if (result != 0) {
			if (result == 1) {
				JsonResponseType = Constants.getEntityType.get(query.collection);
			} else if (result == 2) {
				JsonResponseType = Constants.getListoFEntityType.get(query.collection);
			}

			jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
			
		}
	}
	return jsonresponse;
}

	
	
	/** Find method alternative to query of get
	 * 
	 * @param query
	 * @return
	 * @throws ParseException 
	 * @throws ClientException 
	 */
	public JsonResponse<? extends T1Entity> find(QueryCriteria query) throws ClientException, ParseException  {
		String qParamVal = getService.find(query);
		query.query = qParamVal;		
		return this.get(query);
		
	}
	
	
	/**
	 *  Delete Method for strategy day parts-> budget flight -> strategy-concepts
	 * @param T - i.e. Entity with id and extra params if required
	 * @return
	 * @throws ClientException
	 * @throws ParseException
	 */
	public JsonResponse<? extends T1Entity> delete(StrategyConcept strategyConcept) throws ClientException, ParseException  {
		JsonResponse<? extends T1Entity> jResponse = null;
		if(isAuthenticated()) {
			jResponse = postService.delete(strategyConcept);
		}
		return jResponse;
	}


	/**
	 *  Delete Method for strategy day parts
	 * @param T - i.e. Entity with id and extra params if required
	 * @return
	 * @throws ClientException
	 * @throws ParseException
	 */
	public JsonResponse<? extends T1Entity> delete(StrategyDayPart strategyDayPart) throws ClientException, ParseException  {
		JsonResponse<? extends T1Entity> jResponse = null;
		if(isAuthenticated()) {
			jResponse = postService.delete(strategyDayPart);
		}
		return jResponse;
	}


	/*
	 * getters and setters
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}

	public T1Response getUser() {
		return user;
	}

	public void setUser(T1Response user) {
		this.user = user;
	}
	
}
package pl.execon.osmapi;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import pl.execon.osmapi.dto.Point;
import pl.execon.osmapi.dto.RoutingAPIInstruction;
import pl.execon.osmapi.dto.RoutingAPIPath;
import pl.execon.osmapi.dto.RoutingAPIQuery;
import pl.execon.osmapi.dto.RoutingAPIRawResponse;
import pl.execon.osmapi.endpoint.GenericEndpoint;
import pl.execon.osmapi.util.EncodedPolylineDecoder;
import pl.execon.osmapi.util.Settings;

import com.google.gson.Gson;

public class RoutingAPI {
	private GenericEndpoint endpoint;
	
	public RoutingAPI(){
		endpoint = new GenericEndpoint();
	}

	public GenericEndpoint getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(GenericEndpoint endpoint) {
		this.endpoint = endpoint;
	}
	
	/**
	 * Calculates car route for given query
	 * @param query route query
	 * @param locale
	 * @return
	 */
	public RoutingAPIPath findPath(RoutingAPIQuery query, Locale locale){		
		RoutingAPIRawResponse rawResponse = findPathRaw(query);
		return convert(rawResponse,locale);
	}
	
	protected RoutingAPIPath convert(RoutingAPIRawResponse rawResponse, Locale locale){
		RoutingAPIPath result = new RoutingAPIPath();
		boolean pathFound = rawResponse.getStatus()==0?true:false;

		result.setPathFound(pathFound);
		result.setTotalDistance(rawResponse.getRoute_summary().getTotal_distance());
		result.setTotalTime(rawResponse.getRoute_summary().getTotal_time());

		List<Point> decoded = EncodedPolylineDecoder.decodePoly(rawResponse.getRoute_geometry(), 6);
		result.setLatLonsOnPath(decoded.toArray(new Point[decoded.size()]));

		List<RoutingAPIInstruction> instructions = new LinkedList<RoutingAPIInstruction>();

		ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);
		
		if(rawResponse.getRoute_instructions()!=null){
			for(Object[] singleInstructionArray:rawResponse.getRoute_instructions()){
				RoutingAPIInstruction instruction = new RoutingAPIInstruction();

				String instructionCode = String.valueOf(singleInstructionArray[0]);
				String targetName = String.valueOf(singleInstructionArray[1]);
				Double distance = Double.parseDouble(String.valueOf(singleInstructionArray[2]));
				String direction = String.valueOf(singleInstructionArray[6]);


				String exitNumber = "";

				// for roundabout (11) exit number is passed after a hyhphen: 11-2 means second exit
				if(instructionCode.startsWith("11")){
					exitNumber = instructionCode.split("-")[1];
					instructionCode = instructionCode.split("-")[0];
				}			




				String[] messageArguments = new String[]{				
						targetName,
						exitNumber
				};


				String description = "";			
				MessageFormat formatter;

				switch(instructionCode){
				case "1":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.GoStraight"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				case "2":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.GoRightSlight"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				case "3":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.GoRight"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				case "4":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.GoRightSharp"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				case "5":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.UTurn"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				case "6":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.GoLeftSharp"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				case "7":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.GoLeft"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				case "8":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.GoLeftSlight"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				case "10":
					switch(direction){
					case "E":
						formatter = new MessageFormat(messages.getString("Routing.Instruction.Go.East"));
						formatter.setLocale(locale);
						description = formatter.format(messageArguments);
						break;
					case "W":
						formatter = new MessageFormat(messages.getString("Routing.Instruction.Go.West"));
						formatter.setLocale(locale);
						description = formatter.format(messageArguments);
						break;
					case "N":
						formatter = new MessageFormat(messages.getString("Routing.Instruction.Go.North"));
						formatter.setLocale(locale);
						description = formatter.format(messageArguments);
						break;
					case "S":
						formatter = new MessageFormat(messages.getString("Routing.Instruction.Go.South"));
						formatter.setLocale(locale);
						description = formatter.format(messageArguments);
						break;
					case "NW":
						formatter = new MessageFormat(messages.getString("Routing.Instruction.Go.NorthWest"));
						formatter.setLocale(locale);
						description = formatter.format(messageArguments);
						break;
					case "NE":
						formatter = new MessageFormat(messages.getString("Routing.Instruction.Go.NorthEast"));
						formatter.setLocale(locale);
						description = formatter.format(messageArguments);
						break;
					case "SW":
						formatter = new MessageFormat(messages.getString("Routing.Instruction.Go.SouthWest"));
						formatter.setLocale(locale);
						description = formatter.format(messageArguments);
						break;
					case "SE":
						formatter = new MessageFormat(messages.getString("Routing.Instruction.Go.SouthEast"));
						formatter.setLocale(locale);
						description = formatter.format(messageArguments);
						break;
					}																 
					break;
				case "11":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.Roundabout"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				case "15":
					formatter = new MessageFormat(messages.getString("Routing.Instruction.ReachedDestination"));
					formatter.setLocale(locale);
					description = formatter.format(messageArguments);												 
					break;
				}
				instruction.setInstructionCode(instructionCode);
				instruction.setDescription(description);
				instruction.setObjName(targetName);
				instruction.setDistance(distance.longValue());

				instructions.add(instruction);
			}		
		}
		result.setInstructions(instructions.toArray(new RoutingAPIInstruction[instructions.size()]));
		return result;
	}
	

	protected RoutingAPIRawResponse findPathRaw(RoutingAPIQuery query){
		String queryPart = "";
		queryPart += "loc="+query.getViaPoints()[0].getLatitude()+","+query.getViaPoints()[0].getLongitude();
		queryPart += "&loc="+query.getViaPoints()[1].getLatitude()+","+query.getViaPoints()[1].getLongitude();
		
		if(query.isWithInstructions()){
			queryPart += "&instructions=true";
		}
		
		String url = "http://"+Settings.ENDPOINT_ROUTING_API_BASE_URL+"?"+queryPart;
		
		String response = endpoint.requestURL(url);
					
		RoutingAPIRawResponse rawResult = new Gson().fromJson(response, RoutingAPIRawResponse.class);
		
		return rawResult;
	}
	

}

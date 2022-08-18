package com.crud.api.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.api.Endpoints;
import com.crud.api.dao.AttendanceRepo;
import com.crud.api.dao.TicketDao;
import com.crud.api.encanddec.AESUtil;
import com.crud.api.model.Attendance;
import com.crud.api.model.Ticket;
import com.crud.api.service.TicketService;

import net.bytebuddy.utility.RandomString;

@RestController
//@RequestMapping("/ticket")
@CrossOrigin(origins = "*")
public class TicketController implements Endpoints {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private TicketDao dao;

	@Autowired
	private AttendanceRepo attendanceRepo;
	/*
	 * public String base64Encode(String PIData) { String
	 * data=Base64.getEncoder().encodeToString(PIData.getBytes()); return data; }
	 */

	@PostMapping(Endpoints.post)
	public String bookTicket(@RequestBody Ticket tickets) throws Exception {
		String encryptQuery = AESUtil.encryption(tickets.getCategory());
		String encryptQuery1 = AESUtil.encryption(tickets.getName());
		String urlEncodedData = URLEncoder.encode(encryptQuery, "UTF-8");
		String urlEncodedData1 = URLEncoder.encode(encryptQuery1, "UTF-8");
		tickets.setCategory(urlEncodedData);
		tickets.setName(urlEncodedData1);
//		Ticket t=new Ticket(23,5000,"Diamond","Darkwater");
//		SecretKey key = AESUtil.generateKey(128);
//		IvParameterSpec ivParameterSpec = AESUtil.generateIv();
//		String algorithm = "AES/CBC/PKCS5Padding";
//		SealedObject sealedObject = AESUtil.encryptObject(algorithm, t, key, ivParameterSpec);
//		System.out.println("SO: "+sealedObject);
		ticketService.save(tickets);
		return "Data Stored in Encrypted Format...";
	}

	@GetMapping("/getTickets")
	public List<Ticket> getTickets() throws Exception {
		List<Ticket> allTickets = ticketService.findAll();
//		String decryptQuery = AESUtil.decryption(null);
//		String urlEncodedData = URLDecoder.decode(decryptQuery, "UTF-8");
		return allTickets;
	}

	@GetMapping("/getById/{id}")
	public Optional<Ticket> getTicketById(@PathVariable int id) {
		return ticketService.findById(id);
	}

	@DeleteMapping("/deleteTicket/{id}")
	public List<Ticket> deleteTicket(@PathVariable int id) {
		ticketService.deleteById(id);
		return ticketService.findAll();
	}

	@GetMapping("/get/{category}")
	public Ticket getTicketByCat(@PathVariable String category) {
		return dao.findByCategory(category);
	}

	@PostMapping("/loginTime")
	public String getLoginTime(@RequestBody Attendance at) {
		String token = RandomString.make(32);
//		at.setAttendanceToken(token);
		attendanceRepo.save(at);
		return "Login Time Captured";
	}

	@PutMapping("/logoutTime/{id}")
	public String getLogOutTime(@PathVariable Long id, Date logOutTime, String attendanceToken) throws Exception {
		Attendance at = attendanceRepo.findById(id).orElseThrow(() -> new Exception());
		if (at.getLogOutTime() == null) {
			at.setLogOutTime(new Date());
//			at.setAttendanceToken(null);
			attendanceRepo.save(at);
			return "Logout Time Captured";
		} else
			return "You are already Logged Out";
	}
}

/*
 * System.out.println(at.get().getId());
 * System.out.println(at.get().getLoginTime());
 * if(at.get().getLoginTime()!=null) { Date date = new Date(); String
 * strDateFormat = "HH:mm:ss"; SimpleDateFormat sdf = new
 * SimpleDateFormat(strDateFormat); System.out.println(sdf.format(date)); }
 */
//String s1 = tickets.base64Encode(tickets.getCategory());
//tickets.setCategory(s1);
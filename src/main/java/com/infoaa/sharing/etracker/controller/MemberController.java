package com.infoaa.sharing.etracker.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.infoaa.sharing.etracker.model.Member;
import com.infoaa.sharing.etracker.model.MemberExpense;
import com.infoaa.sharing.etracker.service.CategoryService;
import com.infoaa.sharing.etracker.service.MemberExpenseService;
import com.infoaa.sharing.etracker.service.MemberService;
import com.infoaa.sharing.etracker.util.EmailSender;
import com.infoaa.sharing.etracker.util.ExcelToCsv;
import com.infoaa.sharing.etracker.util.WriteExcel;

@Controller
public class MemberController {

	@Autowired
	private ExcelToCsv exceltocsv;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private WriteExcel writeExcel;

	@Autowired
	private MemberService memberService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MemberExpenseService memberExpenseService;

	@Value("${address}")
	private String address;

	@Value("${excelFilePath}")
	private String excelFilePath;

	@Value("${rootPath}")
	private String rootPath;

	private List<String> memberNames;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields(new String[] { "category" });
		binder.setDisallowedFields(new String[] { "checkboxgroup" });
		binder.setDisallowedFields(new String[] { "member" });


		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "createdDate", new CustomDateEditor(format, false));
	}

	@RequestMapping(value = "/trackMember", method = RequestMethod.GET)
	public String trackMember(Model model){

		memberNames =  memberService.getFullName();

		model.addAttribute("members", memberNames);
		List<String> categories = categoryService.getOnlyCategories();
		model.addAttribute("categories", categories);
		String addressAddMember = address+"addMembers";
		model.addAttribute("addMember", addressAddMember);
		String addMemberUrl = address+"addMember";
		model.addAttribute("address", addMemberUrl);
		String view = address+"MemberTrackData";
		model.addAttribute("viewPage",view );
		String group = address+"GroupExpense";
		model.addAttribute("group", group);
		return "trackMember";
	}

	@RequestMapping(value = "/GroupExpense", method = RequestMethod.GET)
	public String group(Model model){
		List<String> members = memberService.getFullName();
		List<String> categories = categoryService.getOnlyCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("members", members);
		return "GroupExpense";
	}

	@RequestMapping(value = "/getGroupData", method = RequestMethod.POST)
	public String getGroupData(Model model,
			@RequestParam("checkboxgroup") String[] names,
			@RequestParam("category") String category,
			@RequestParam("createdDate") String date,
			@RequestParam("comments") String comments,
			@RequestParam("amount") double amount,
			@RequestParam("expenseName") String expenseName) throws ParseException{

		ArrayList<Integer> memberIds = new ArrayList<Integer>();
		int categoryId = categoryService.getCategoryId(category);
		for (String string : names) {
			memberIds.add(memberExpenseService.getMemberId(string));
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date sdate = format.parse(date);
		int splitAmount = (int) (amount/memberIds.size());
		for (Integer memberId : memberIds) {
			MemberExpense expense = new MemberExpense();
			expense.setMemberId(memberId);
			expense.setAmount(splitAmount);
			expense.setCategoryId(categoryId);
			expense.setComments(comments);
			expense.setExpenseName(expenseName);
			expense.setCreatedDate(sdate);

			memberExpenseService.saveMemberExpense(expense);
		}

		return "redirect:/trackMember";
	}

	@RequestMapping(value = "/MemberTrackData", method = RequestMethod.GET)
	public String viewPage(Model model){
		memberNames =  memberService.getFullName();

		model.addAttribute("members", memberNames);
		return "MemberTrackData";
	}


	@RequestMapping(value = "/addMember", method = RequestMethod.GET)
	public String addMemberPage(Model model){
		Member member = new Member();
		model.addAttribute("member", member);
		return "addMember";
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
	public String hello(@Valid @ModelAttribute Member member, Model model, BindingResult result) {

		if(result.hasErrors()){
			return "addMember";
		}

		memberService.addMember(member);
		return "redirect:/trackMember";
	}

	@RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
	public String deleteMember(@RequestParam("deleteMember") String memberName){
		memberExpenseService.deleteMemberExpense(memberName);
		memberService.deleteMember(memberName);
		return "redirect:/trackMember";
	}

	@RequestMapping(value = "/memberExpenseSubmission", method = RequestMethod.POST)
	public String hello(@Valid @ModelAttribute MemberExpense memberExpense, Model model, BindingResult result,
			@RequestParam("member") String fullName, @RequestParam("category") String category) {

		if(result.hasErrors()){
			return "trackMember";
		}

		int memberId = memberExpenseService.getMemberId(fullName);
		memberExpense.setMemberId(memberId);
		int categoryId = categoryService.getCategoryId(category);
		memberExpense.setCategoryId(categoryId);
		memberExpenseService.saveMemberExpense(memberExpense);
		return "redirect:/trackMember";
	}

	@RequestMapping(value = "/memberTracker", method = RequestMethod.POST)
	public String etracker(@RequestParam("trackMember") String name, Model model) throws IOException {
		double total = 0;
		int memberId = memberExpenseService.getMemberId(name);
		//List<MemberExpense> expenses = memberExpenseService.getExpenses(memberId);

		List<Object[]> list = memberExpenseService.getExpenseAndCategoryDetails(memberId);


		model.addAttribute("customList", list);
		//model.addAttribute("listOfExpense", expenses);
		for (int i=0; i<list.size(); i++){
			Object[] row = list.get(i);
			//System.out.println("Element "+i+Arrays.toString(row));
			double one = (double) row[1];
			total = total +one;
		}

		//String excelFilePath = "C:/Users/Balaji/Desktop/Download/excel.xls";
		writeExcel.writeExcel(list, excelFilePath);
		System.out.println("Success!!!!!!");

		model.addAttribute("name", name);
		model.addAttribute("total",total);
		return "MemberExpenseByname";
	}

	@RequestMapping(value = "/deleteExpenseFromMember", method = RequestMethod.POST)
	public String deleteExpenseDate(Model model, @RequestParam("checkboxgroup") String[] expenseIds){
		int[] result = new int[expenseIds.length];
		for (int i = 0; i < expenseIds.length; i++) {
			result[i] = Integer.parseInt(expenseIds[i]);
		}
		memberExpenseService.deleteExpenses(result);
		return "redirect:/trackMember";
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/sendEmail" , method = RequestMethod.POST)
	public String sendMail(@RequestParam("email") String mailTo){

		String message = "Please Download the attachment";
		try {
			emailSender.sendEmailWithAttachments(mailTo,
					"SVS Expense Sheet", message);
			System.out.println("Email sent.");

		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();

		}

		finally{
			return "redirect:/trackMember";
		}
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadXls(@RequestParam("name") String name,@RequestParam("trackMember") String memberName,
			@RequestParam("file") MultipartFile file, Model model){

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				//String rootPath ="C:/Users/Balaji/Desktop/Download";
				File dir = new File(rootPath + File.separator + "Server Files");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name+".xls");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				exceltocsv.caller(serverFile,memberName);
				model.addAttribute("message", "You successfully uploaded file=" + name);

				return "result";
			} catch (Exception e) {
				model.addAttribute("message", "You failed to upload " + name + " => " + e.getMessage());
				return "result";
			}
		} else {
			model.addAttribute("message", "You failed to upload " + name
					+ " because the file was empty.");
			return "result";
		}

	}

}

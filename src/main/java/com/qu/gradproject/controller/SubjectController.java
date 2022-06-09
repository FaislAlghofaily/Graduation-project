package com.qu.gradproject.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qu.gradproject.dto.AnnouncementDTO;
import com.qu.gradproject.dto.AttendanceDTO;
import com.qu.gradproject.dto.HomeworkDTO;
import com.qu.gradproject.entity.AttendanceEntity;
import com.qu.gradproject.entity.CodeCheckEntity;
import com.qu.gradproject.entity.FilesEntity;
import com.qu.gradproject.entity.GroupsEntity;
import com.qu.gradproject.entity.UserEntity;
import com.qu.gradproject.service.AnnouncementService;
import com.qu.gradproject.service.AttendanceService;
import com.qu.gradproject.service.FilesService;
import com.qu.gradproject.service.GroupsService;
import com.qu.gradproject.service.HomeworkService;
import com.qu.gradproject.service.UserService;

@Controller
@RequestMapping(value = "/subjects")
public class SubjectController {

	@Autowired
	GroupsService groupsService;

	@Autowired
	FilesService filesService;

	@Autowired
	UserService userService;
	@Autowired
	HomeworkService homeworkService;

	@Autowired
	AnnouncementService announcementService;

	@Autowired
	AttendanceService attendanceService;

	@GetMapping(value = "/dashboard")
	public String instructorDashboard(Principal principal, Model model) {

		model.addAttribute("grouplist",
				groupsService.getGroupEntityByUserEntity(userService.getUserEntityByEmail(principal.getName())));
		return "courses";
	}

	@GetMapping(value = "/subject/{id}")
	public String subject(@PathVariable(value = "id") long id, Principal principal, Model model) {

		model.addAttribute("groupentity", groupsService.getGroupEntityById(id));
		return "redirect:/subjects/attendance/" + id;
	}

	// content
	@GetMapping(value = "/content/{id}")
	public String subjectContent(@PathVariable(value = "id") long id, Principal principal, Model model) {
		model.addAttribute("groupentity", groupsService.getGroupEntityById(id));
		model.addAttribute("user", userService.getUserEntityByEmail(principal.getName()));
		model.addAttribute("files", filesService.getContentFilesByGroup(id));
		return "contentpagee";
//		return "content";
	}

	@PostMapping(value = "/fileupload/{id}")
	public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile,
			@PathVariable(value = "id") long id, Principal principal) {
		if (multipartFile.isEmpty()) {
			return "redirect:/subjects/content/" + id;

		}

		filesService.saveFile(multipartFile, id, "content", userService.getUserEntityByEmail(principal.getName()));
		return "redirect:/subjects/content/" + id;

	}

	@GetMapping(value = "/content/delete/{id}")
	public String deleteContent(@PathVariable(value = "id") long id) {

		return "redirect:/subjects/content/" + filesService.deleteContentById(id);
	}

	// homework
	@GetMapping(value = "/homework/{id}")
	public String subjectHomework(@PathVariable(value = "id") long id, Principal principal, Model model) {
		HomeworkDTO homeworkDTO = new HomeworkDTO();
		model.addAttribute("homeworkDTO", homeworkDTO);
		model.addAttribute("groupentity", groupsService.getGroupEntityById(id));
		model.addAttribute("user", userService.getUserEntityByEmail(principal.getName()));
		model.addAttribute("homeworks", homeworkService.getAllAvailableHomeworksByGroup(id,
				userService.getUserEntityByEmail(principal.getName())));
		return "homeworkpage1";
//		return "homework";

	}

	@PostMapping("/homework/new/{id}")
	public String saveHomework(@ModelAttribute("homeworkDTO") HomeworkDTO homeworkDTO,
			@PathVariable(value = "id") long id) {
		if (homeworkDTO.getTitle().isEmpty() || homeworkDTO.getDeadline() == null) {
			return "redirect:/subjects/homework/" + id;
		}

		homeworkService.createNewHomework(homeworkDTO, id);
		return "redirect:/subjects/homework/" + id;
	}

	@GetMapping(value = "/homework/submit/{id}/{homeworkid}")
	public String homeworkSubmitPage(@PathVariable(value = "id") long id,
			@PathVariable(value = "homeworkid") long homeworkId, Model model, Principal principal) {
		model.addAttribute("groupentity", groupsService.getGroupEntityById(id));
		model.addAttribute("homeworkid", homeworkId);

		if (filesService.checkIfSubmitted(homeworkService.getHomeworkById(homeworkId),
				userService.getUserEntityByEmail(principal.getName()))) {
			model.addAttribute("Submitted", true);
		} else {
			model.addAttribute("Submitted", false);
		}

//		return "homeworkupload";
		
		return "homeworkuploadpage1";
	}

	@PostMapping(value = "/homework/fileupload/{id}/{homeworkid}")
	public String homeworkUpload(@RequestParam("fileUpload") MultipartFile multipartFile,
			@PathVariable(value = "id") long id, @PathVariable(value = "homeworkid") long homeworkId,
			Principal principal) {

		if (multipartFile.isEmpty()) {
			return "redirect:/subjects/homework/" + id;

		}

		filesService.saveHomeworkFile(multipartFile, id, "homework",
				userService.getUserEntityByEmail(principal.getName()), homeworkId);
		return "redirect:/subjects/homework/" + id;

	}

	@GetMapping(value = "/homework/list/{id}/{homeworkid}")
	public String homeworkListPage(@PathVariable(value = "id") long id,
			@PathVariable(value = "homeworkid") long homeworkId, Model model) {
		model.addAttribute("files", filesService.getAllFilesByHomeworkId(homeworkId));
		model.addAttribute("groupentity", groupsService.getGroupEntityById(id));
//		return "homeworklist";
		return "homeworklistpage1";
	}

	@GetMapping(value = "/homework/delete/{id}")
	public String deleteHomework(@PathVariable(value = "id") long id) {

		return "redirect:/subjects/homework/" + homeworkService.deleteHomeworkById(id);
	}

	// Announcements

	@GetMapping(value = "/announcement/{id}")
	public String subjectAnnouncement(@PathVariable(value = "id") long id, Principal principal, Model model) {
		AnnouncementDTO announcementDTO = new AnnouncementDTO();
		model.addAttribute("announcementDTO", announcementDTO);
		model.addAttribute("groupentity", groupsService.getGroupEntityById(id));
		model.addAttribute("user", userService.getUserEntityByEmail(principal.getName()));
		model.addAttribute("announcements", announcementService.getAllAnnouncementByGroupId(id));
//		return "announcement";
		return "announcementpage1";
	}

	@PostMapping("/announcement/new/{id}")
	public String saveAnnouncement(@ModelAttribute("announcementDTO") AnnouncementDTO announcementDTO,
			@PathVariable(value = "id") long id) {
		if (announcementDTO.getTitle().isEmpty()) {
			return "redirect:/subjects/announcement/" + id;
		}
		announcementService.createNewAnnouncement(announcementDTO, id);
		return "redirect:/subjects/announcement/" + id;
	}

	// attendance
	@GetMapping(value = "/attendance/{id}")
	public String subjectAttendance(@PathVariable(value = "id") long id, Principal principal, Model model) {
		UserEntity userEntity = userService.getUserEntityByEmail(principal.getName());
		GroupsEntity groupsEntity = groupsService.getGroupEntityById(id);
		model.addAttribute("groupentity", groupsEntity);
		model.addAttribute("user", userEntity);
		AttendanceDTO attendanceDTO = new AttendanceDTO();
		model.addAttribute("attendanceDTO", attendanceDTO);
		CodeCheckEntity codeCheckEntity = attendanceService.getCodeByGroup(id);
		model.addAttribute("codecheckentity", codeCheckEntity);

		boolean attendanceSubmitted = false;
		// cheacking if attendance taken for the user or not
		if (userEntity.getRole().contains("Student")) {
			if (attendanceService.checkIfAttendanceTaken(userEntity, groupsEntity)) {
				attendanceSubmitted = true;

			}
		}

		model.addAttribute("attendanceSubmitted", attendanceSubmitted);

//		return "attendancepage";
		return "Attendance1";

	}

	@GetMapping(value = "/attendance/newcode/{id}")
	public String startNewAttendanceSession(@PathVariable(value = "id") long id) {
		attendanceService.newAttendanceSession(id);
		return "redirect:/subjects/attendance/" + id;
	}

	@PostMapping("/attendance/validation/{id}")
	public String attendanceValidation(@ModelAttribute("attendanceDTO") AttendanceDTO attendanceDTO,
			@PathVariable(value = "id") long id, Principal principal) {
		if (attendanceDTO.getCodeOfAttendance().isEmpty()) {
			return "redirect:/subjects/attendance/" + id;

		}

		attendanceService.takeAttendance(userService.getUserEntityByEmail(principal.getName()),
				attendanceDTO.getCodeOfAttendance(), id);
		return "redirect:/subjects/attendance/" + id;
	}

	@GetMapping(value = "/attendance/all/{id}")
	public String subjectAllOfAttendance(@PathVariable(value = "id") long id, Principal principal, Model model,
			@ModelAttribute("date") String dateOfLecture) {

		model.addAttribute("groupentity", groupsService.getGroupEntityById(id));
		model.addAttribute("user", userService.getUserEntityByEmail(principal.getName()));

		AttendanceDTO attendanceDTO = new AttendanceDTO();
		attendanceDTO.setDateOfLecture(dateOfLecture);
		model.addAttribute("attendanceDTO", attendanceDTO);

		List<AttendanceDTO> attendance = new ArrayList<>();

		if (dateOfLecture.length() > 1) {
			attendance = attendanceService.getAttendanceByDate(id, dateOfLecture);

		}
		model.addAttribute("attendancelist", attendance);
		List<String> dates = new ArrayList<>();
		for (LocalDate date : attendanceService.getAllPreviosAttendance(id)) {
			dates.add(date.toString());
		}

		model.addAttribute("dates", dates);

//		return "previousAttendance";
		return "previousAttendancepage1";
	}

	@PostMapping("/attendance/date/{id}")
	public String subjectAttendanceforDate(@ModelAttribute("attendanceDTO") AttendanceDTO attendanceDTO,
			RedirectAttributes redirectAttributes, @PathVariable(value = "id") long id) {
		if (attendanceDTO.getDateOfLecture().contains("Select one")) {
			return "redirect:/subjects/attendance/all/" + id;
		}

		redirectAttributes.addFlashAttribute("date", attendanceDTO.getDateOfLecture());
		return "redirect:/subjects/attendance/all/" + id;
	}

	@GetMapping("/attendance/present/{id}/{studentid}/{date}")
	public String changeAttendanceToPresent(@PathVariable(value = "id") long id,
			@PathVariable(value = "studentid") long studentid, @PathVariable(value = "date") String date,
			RedirectAttributes redirectAttributes) {
		attendanceService.changeAttendanceToPresent(studentid, id, date);
		redirectAttributes.addFlashAttribute("date", date);
		return "redirect:/subjects/attendance/all/" + id;
	}

	@GetMapping("/attendance/absent/{id}/{studentid}/{date}")
	public String changeAttendanceToAbsent(@PathVariable(value = "id") long id,
			@PathVariable(value = "studentid") long studentid, @PathVariable(value = "date") String date,
			RedirectAttributes redirectAttributes) {
		attendanceService.changeAttendanceToAbsent(studentid, id, date);
		redirectAttributes.addFlashAttribute("date", date);
		return "redirect:/subjects/attendance/all/" + id;
	}

}

package com.alexsoderberg.personal.appointment_service.controller;

import com.alexsoderberg.personal.appointment_service.model.Appointment;
import com.alexsoderberg.personal.appointment_service.service.AppointmentService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // Import for POST
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // Import for status assertions

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppointmentService service;

    @Test
    void createAppointmentShouldReturn201CreatedWhenInputIsValid() throws Exception {
        // Arrange
        Appointment mockAppointment = new Appointment(
            "John Doe",
            LocalDate.of(2024, 12, 1),
            LocalTime.of(10, 0),
            60,
            "Consultation"
        );

        Mockito.when(service.saveAppointment(any(Appointment.class))).thenReturn(mockAppointment);

        mockMvc.perform(post("/api/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name": "John Doe",
                        "date": "2024-12-01",
                        "time": "10:00",
                        "duration": 60,
                        "description": "Consultation"
                    }
                """))
                .andExpect(status().isCreated());
    }

    @Test
    void getAppointmentsShouldReturnListOfAppointments() throws Exception {
      List<Appointment> mockAppointments = List.of(
        new Appointment(
          "Test Testsson",
          LocalDate.now().plusDays(2),
          LocalTime.now(),
          90,
          "Male haircut"
        ),
        new Appointment(
          "Testa McTest",
          LocalDate.now().plusDays(1),
          LocalTime.now(),
          60,
          "Female haircut"
        )
      );

      when(service.getAppointments()).thenReturn(mockAppointments);

      mockMvc.perform(get("/api/appointments")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(2))
        .andExpect(jsonPath("$[0].name").value("Test Testsson"))
        .andExpect(jsonPath("$[1].name").value("Testa McTest"));
    }

    @Test
    void getAppointmentsShouldReturnEmptyListWhenNoSavedAppointments() throws Exception {
      when(service.getAppointments()).thenReturn(List.of());

      mockMvc.perform(get("/api/appointments")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void deleteAppointmentShouldReturnDeletedAppointment() throws Exception {
      Optional<Appointment> appointment = Optional.of(new Appointment(
        "Test Testsson",
        LocalDate.now().plusDays(2),
        LocalTime.now(),
        90,
        "Male haircut"
      ));

      Long appointmentId = (long) 1;

      when(service.deleteAppointment(appointmentId)).thenReturn(appointment);

      mockMvc.perform(delete("/api/appointments/" + appointmentId)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
      
      verify(service, times(1)).deleteAppointment(appointmentId);
    }
}


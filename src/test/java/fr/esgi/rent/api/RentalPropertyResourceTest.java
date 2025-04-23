package fr.esgi.rent.api;

import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import fr.esgi.rent.mapper.RentalPropertyDtoMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static fr.esgi.rent.samples.RentalPropertyDtoSample.rentalPropertyResponseList;
import static fr.esgi.rent.samples.RentalPropertyEntitySample.rentalPropertyEntities;
import static fr.esgi.rent.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalPropertyResource.class)
class RentalPropertyResourceTest {

    @Value("classpath:/json/rentalProperties.json")
    private Resource rentalProperties;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RentalPropertyRepository rentalPropertyRepository;

    @MockitoBean
    private RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @Test
    void shouldGetRentalProperties() throws Exception {
        List<RentalPropertyEntity> rentalPropertyEntities = rentalPropertyEntities();
        List<RentalPropertyResponseDto> rentalPropertyResponseList = rentalPropertyResponseList();

        when(rentalPropertyRepository.findAll()).thenReturn(rentalPropertyEntities);
        when(rentalPropertyDtoMapper.mapToDtoList(rentalPropertyEntities)).thenReturn(rentalPropertyResponseList);

        mockMvc.perform(get("/api/rental-properties"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(content().json(readResource(rentalProperties)));

        verify(rentalPropertyRepository).findAll();
        verify(rentalPropertyDtoMapper).mapToDtoList(rentalPropertyEntities);
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

}

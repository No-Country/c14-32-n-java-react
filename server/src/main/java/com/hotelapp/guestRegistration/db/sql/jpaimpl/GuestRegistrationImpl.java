package com.hotelapp.guestRegistration.db.sql.jpaimpl;

import com.hotelapp.guestRegistration.db.sql.jparepository.GuestRegistrationRepository;
import com.hotelapp.guestRegistration.db.sql.mapper.GuestRegistrationMapper;
import com.hotelapp.guestRegistration.db.sql.modeldata.GuestRegistrationData;
import com.hotelapp.guestRegistration.dto.model.GuestRegistration;
import com.hotelapp.guestRegistration.facade.CreateRegistrationFacade;
import com.hotelapp.guestRegistration.facade.EndOfStayFacade;
import com.hotelapp.guestRegistration.facade.GetAllRegistrationFacade;
import com.hotelapp.guestRegistration.facade.GetGuestRegistrationByIdFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestRegistrationImpl implements CreateRegistrationFacade, GetAllRegistrationFacade, EndOfStayFacade,
        GetGuestRegistrationByIdFacade {

    private final GuestRegistrationRepository guestRegistrationRepository;
    private final GuestRegistrationMapper guestRegistrationMapper;

    public GuestRegistrationImpl(GuestRegistrationRepository guestRegistrationRepository,
                                 GuestRegistrationMapper guestRegistrationMapper){
        this.guestRegistrationRepository =guestRegistrationRepository;
        this.guestRegistrationMapper = guestRegistrationMapper;
    }

    @Override
    public GuestRegistration save(GuestRegistration guestRegistration) {
        GuestRegistrationData guestRegistrationData = guestRegistrationRepository
                .save(guestRegistrationMapper
                .guestRegistrationToGuestRegistrationData(guestRegistration));
        return guestRegistrationMapper.guestRegistrationDataToGuestRegistration(guestRegistrationData);
    }

    @Override
    public Page<GuestRegistration> getAllPaginator(int numberPage) {
        int pageSize = 10;
        PageRequest page = PageRequest.of(numberPage, pageSize);
        return guestRegistrationRepository.findAll(page).map(guestRegistrationMapper::guestRegistrationDataToGuestRegistration);
    }

    @Override
    public GuestRegistration updateGuestRegistration(GuestRegistration guestRegistration) {
        Optional<GuestRegistrationData> guestRegistrationDataOptional = guestRegistrationRepository.
                findById(guestRegistration.getIdRegistration());
        if (guestRegistrationDataOptional.isPresent()){
            GuestRegistrationData guestRegistrationData = guestRegistrationRepository.
                    save(guestRegistrationMapper.guestRegistrationToGuestRegistrationData(guestRegistration));
            return guestRegistrationMapper.guestRegistrationDataToGuestRegistration(guestRegistrationData);
        }
        return null;
    }

    @Override
    public GuestRegistration getById(Long id) {
        Optional<GuestRegistrationData> guestRegistrationDataOptional = guestRegistrationRepository.findById(id);
        if (guestRegistrationDataOptional.isPresent()){
            GuestRegistrationData guestRegistrationData = guestRegistrationDataOptional.get();
            return guestRegistrationMapper.guestRegistrationDataToGuestRegistration(guestRegistrationData);
        }
        return null;
    }
}

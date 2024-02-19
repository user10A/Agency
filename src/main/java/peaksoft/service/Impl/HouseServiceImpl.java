package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Agency;
import peaksoft.model.House;
import peaksoft.repo.HouseRepository;
import peaksoft.service.HouseService;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    HouseRepository houseRepository;

    @Override
    public void saveHouse(House house, Long agencyId) {
        houseRepository.saveHouse(house,agencyId);
    }

    @Override
    public void updateHouse(Long id, House house) {
        houseRepository.updateHouse(id,house);
    }

    @Override
    public void deleteHouse(Long id) {
        houseRepository.deleteHouse(id);
    }

    @Override
    public House getHouseById(Long id) {
        return houseRepository.getHouseById(id);
    }

    @Override
    public List<House> getAllHouse() {
        return houseRepository.getAllHouse();
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agencyId) {
        return houseRepository.getAllHouseByAgencyId(agencyId);
    }

    @Override
    public void deleteGetAllHouseByAgencyId(Long agencyId) {
        houseRepository.deleteGetAllHouseByAgencyId(agencyId);
    }
}

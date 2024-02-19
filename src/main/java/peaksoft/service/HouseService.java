package peaksoft.service;

import peaksoft.model.House;

import java.util.List;

public interface HouseService {
    //III - House тузгондо кайсыл agency гe тиешелуу экенин беришибиз керек(agency id)
    void saveHouse(House house,Long agencyId);
    void updateHouse (Long id,House house);
    void deleteHouse(Long id);
    House getHouseById(Long id);
    List<House> getAllHouse();
    List<House> getAllHouseByAgencyId(Long agencyId);
    void deleteGetAllHouseByAgencyId(Long agencyId) ;
}

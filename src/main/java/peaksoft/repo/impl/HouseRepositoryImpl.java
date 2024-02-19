package peaksoft.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Agency;
import peaksoft.model.House;
import peaksoft.repo.HouseRepository;

import java.util.List;

@Transactional
@Repository
public class HouseRepositoryImpl implements HouseRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveHouse(House house, Long agencyId) {
        if (agencyId == null || agencyId <= 0) {
            throw new IllegalArgumentException("Некорректный идентификатор агентства: " + agencyId);
        }

        Agency agency = entityManager.find(Agency.class, agencyId);
        if (agency == null) {
            throw new IllegalArgumentException("Агентство с таким ID " + agencyId + " не найдено");
        }
        if (house.getHouseAgency() != null && !house.getHouseAgency().equals(agency)) {
            throw new IllegalArgumentException("Дом уже принадлежит другому агентству");
        }
        house.setHouseAgency(agency);
        entityManager.persist(house);
        agency.getAgencyHouses().add(house);
    }

    @Override
    public void updateHouse(Long id, House updatedHouse) {
        House existingHouse = entityManager.find(House.class, id);
        if (existingHouse != null) {
            existingHouse.setHouseType(updatedHouse.getHouseType());
            existingHouse.setRoom(updatedHouse.getRoom());
            existingHouse.setAddress(updatedHouse.getAddress());
            existingHouse.setImage(updatedHouse.getImage());
            existingHouse.setPrice(updatedHouse.getPrice());
            existingHouse.setHouseAgency(updatedHouse.getHouseAgency());
            existingHouse.setBooking(updatedHouse.getBooking());
            entityManager.merge(existingHouse);
        } else {
            throw new IllegalArgumentException("Дом с ID " + id + " не найден");
        }
    }


    @Override
    public void deleteHouse(Long id) {
        House house = entityManager.find(House.class,id);
        if (house==null){
            throw new IllegalArgumentException("Дом с ID " + id + " не найден");
        } else {
//          house.setBooking(null);
          house.setHouseAgency(null);
          entityManager.remove(house);
        }
    }

    @Override
    public House getHouseById(Long id) {
        House house =entityManager.find(House.class,id);
        if (house== null){
            throw new IllegalArgumentException("Дом с ID " + id + " не найден");
        }
        return house;
    }

    @Override
    public List<House> getAllHouse() {
        return entityManager.createQuery("select l from House l ",House.class).getResultList();
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agencyId) {
        return entityManager.createQuery("select l from House l where l.houseAgency.id=:agencyId", House.class).
                setParameter("agencyId", agencyId)
                        .getResultList();
    }
    @Override
    public void deleteGetAllHouseByAgencyId(Long agencyId) {
        List<House> listId = entityManager.createQuery("select h from House h where h.houseAgency.id=:agencyId", House.class)
                .setParameter("agencyId", agencyId)
                .getResultList();

        for (House house : listId) {
            entityManager.remove(house);
        }
    }
}

package facades;

import dtos.AddressDTO;
import dtos.CityInfoDTO;
import entities.Address;
import entities.CityInfo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;


public class AddressFacade {

    private static AddressFacade instance;
    private static EntityManagerFactory emf;

    private AddressFacade() {}

    public static AddressFacade getAddressFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public AddressDTO create(AddressDTO ad){
        Address address = new Address(ad);

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AddressDTO(address);
    }

    public CityInfoDTO getCityInfoByZipCode(int zipCode) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CityInfo> query = em.createQuery("SELECT ci FROM CityInfo ci WHERE ci.zipCode = :zip", CityInfo.class);
            query.setParameter("zip", zipCode);
            CityInfo cityInfo = query.getResultList().get(0);
            return new CityInfoDTO(cityInfo);
        } finally {
            em.close();
        }
    }

    public List<AddressDTO> getAllAddressesByZipCode(int zipCode) {
        int cityInfoId = getCityInfoByZipCode(zipCode).getId();

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Address> query = em.createQuery("select a from Address a join a.cityInfo ac where ac.id= :cityInfoId", Address.class);
            query.setParameter("cityInfoId", cityInfoId);
            List<Address> addressList = query.getResultList();
            return AddressDTO.getDTOs(addressList);
        } finally {
            em.close();
        }
    }



}

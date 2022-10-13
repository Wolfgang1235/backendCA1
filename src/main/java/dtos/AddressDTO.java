package dtos;

import entities.Address;
import entities.CityInfo;

import java.util.ArrayList;
import java.util.List;

public class AddressDTO {
    private Integer id;
    private final String street;
    private final String additionalInfo;
    private boolean isPrivate;
    private CityInfo cityInfo;

    public AddressDTO(String street, String additionalInfo, boolean isPrivate, CityInfo cityInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.isPrivate = isPrivate;
        this.cityInfo = cityInfo;
    }

    public AddressDTO(Address address) {
        if(address.getId() != null) {
            this.id = address.getId();
        }
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        this.isPrivate = address.getIsPrivate();
        this.cityInfo = address.getCityInfo();
    }

    public static List<AddressDTO> getDTOs(List<Address> addresses) {
        List<AddressDTO> addressDTOList = new ArrayList<>();
        addresses.forEach(address -> addressDTOList.add(new AddressDTO(address)));
        return addressDTOList;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", isPrivate=" + isPrivate +
                ", CityInfo=" + cityInfo +
                '}';
    }
}

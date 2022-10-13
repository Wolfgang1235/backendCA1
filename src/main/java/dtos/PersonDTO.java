package dtos;

import entities.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO implements Serializable {
    private Integer id;
    @Size(max = 45)
    @NotNull
    private final String email;
    @Size(max = 45)
    @NotNull
    private final String firstName;
    @Size(max = 45)
    @NotNull
    private final String lastName;

    private final Phone phone;
    private final AddressInnerDTO address;

    private final List<HobbyInnerDTO> hobbies = new ArrayList<>();

    public PersonDTO( String email, String firstName, String lastName, Phone phone, AddressInnerDTO address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public PersonDTO(Person person) {
        if(person.getId() != null) {
            this.id = person.getId();
        }
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phone = person.getPhone();

        this.address = new AddressInnerDTO(person.getAddress());

        person.getHobbies().forEach(hobby -> hobbies.add(new HobbyInnerDTO(hobby)));
    }


    public static List<PersonDTO> getDTOs(List<Person> persons) {
        List<PersonDTO> personDTOS = new ArrayList<>();
        persons.forEach(person -> personDTOS.add(new PersonDTO(person)));
        return personDTOS;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Phone getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", addressInnerDTO=" + address +
                ", hobbies=" + hobbies +
                '}';
    }

    public static class HobbyInnerDTO implements Serializable {
        private final Integer id;
        private final String wikiLink;
        private final String name;
        private final String category;
        private final String type;
        private final String description;

        public HobbyInnerDTO(Hobby hobby) {
            this.id = hobby.getId();
            this.wikiLink = hobby.getWikiLink();
            this.name = hobby.getName();
            this.category = hobby.getCategory();
            this.type = hobby.getType();
            this.description = hobby.getDescription();
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "wikiLink = " + wikiLink + ", " +
                    "name = " + name + ", " +
                    "category = " + category + ", " +
                    "type = " + type + ", " +
                    "description = " + description + ")";
        }
    }

    public static class AddressInnerDTO implements Serializable {
        private final Integer id;
        private final String street;
        private final String additionalInfo;
        private final boolean isPrivate;

        public CityInfoInnerDTO cityInfo;


        public AddressInnerDTO(Address address) {
            this.id = address.getId();
            this.street = address.getStreet();
            this.additionalInfo = address.getAdditionalInfo();
            this.isPrivate = address.getIsPrivate();
            this.cityInfo = new CityInfoInnerDTO(address.getCityInfo().getZipCode(),address.getCityInfo().getCityName());
        }

        public Integer getId() {
            return id;
        }

        public boolean isPrivate() {
            return isPrivate;
        }

        @Override
        public String toString() {
            return "AddressInnerDTO{" +
                    "id=" + id +
                    ", street='" + street + '\'' +
                    ", additionalInfo='" + additionalInfo + '\'' +
                    ", isPrivate=" + isPrivate +
                    '}';
        }

        public static class CityInfoInnerDTO implements Serializable {
            private final Integer id;
            private final Integer zipCode;
            private final String cityName;

            public CityInfoInnerDTO(Integer zipCode, String cityName){
                this.id = getId();
                this.zipCode = zipCode;
                this.cityName = cityName;
            }

            public Integer getId() {
                return id;
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "(" +
                        "id = " + id + ", " +
                        "zipCode = " + zipCode + ", " +
                        "cityName = " + cityName + ")";
            }
        }
    }

}
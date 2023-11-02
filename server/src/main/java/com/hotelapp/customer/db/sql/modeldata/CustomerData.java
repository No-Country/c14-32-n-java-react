package com.hotelapp.customer.db.sql.modeldata;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String dni;
    @Column(unique = true)
    private String email;
    private String phone;

    public static final class CustomerDataBuilder {
        private Long idCustomer;
        private String firstname;
        private String lastname;
        private String dni;
        private String email;
        private String phone;

        public CustomerDataBuilder() {
        }

        public static CustomerDataBuilder aCustomerData() {
            return new CustomerDataBuilder();
        }


        public CustomerDataBuilder idCustomer(Long idCustomer) {
            this.idCustomer = idCustomer;
            return this;
        }

        public CustomerDataBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public CustomerDataBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public CustomerDataBuilder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public CustomerDataBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerDataBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerData build() {
            CustomerData customerData = new CustomerData();
            customerData.setIdCustomer(idCustomer);
            customerData.setFirstname(firstname);
            customerData.setLastname(lastname);
            customerData.setDni(dni);
            customerData.setEmail(email);
            customerData.setPhone(phone);
            return customerData;
        }
    }
}

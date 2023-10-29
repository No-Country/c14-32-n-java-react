package com.hotelapp.customer.dto.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long idCustomer;

    @NotBlank(message = "the firstname cannot be blank")
    private String firstname;

    @NotBlank(message = "the lastname cannot be blank")
    private String lastname;

    @Pattern(regexp = "\\d+", message = "the dni must contain only digits")
    @Size(min = 6, max = 8, message = "the dni must be 6 to 8 digits long")
    private String dni;

    @Email(message = "invalid email address")
    private String email;

    @Pattern(regexp = "\\d+", message = "the phone must contain only digits")
    private String phone;


    public static final class CustomerBuilder {
        private Long idCustomer;
        private String firstname;
        private String lastname;
        private String dni;
        private String email;
        private String phone;

        public CustomerBuilder() {
        }

        public static CustomerBuilder aCustomer() {
            return new CustomerBuilder();
        }

        public CustomerBuilder idCustomer(Long idCustomer) {
            this.idCustomer = idCustomer;
            return this;
        }

        public CustomerBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public CustomerBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public CustomerBuilder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Customer build() {
            Customer customer = new Customer();
            customer.setIdCustomer(idCustomer);
            customer.setFirstname(firstname);
            customer.setLastname(lastname);
            customer.setDni(dni);
            customer.setEmail(email);
            customer.setPhone(phone);
            return customer;
        }
    }
}

package de.oglimmer.lazydemo.dao;

import de.oglimmer.lazydemo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

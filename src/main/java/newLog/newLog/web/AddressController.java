package newLog.newLog.web;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import newLog.newLog.dto.AddressDto;
import newLog.newLog.dto.AddressSearchDto;
import newLog.newLog.exception.IdMismatchException;
import newLog.newLog.exception.IdNotExists;
import newLog.newLog.exception.InvalidAddressException;
import newLog.newLog.exception.RequestValidator;
import newLog.newLog.mapper.AddressMapper;
import newLog.newLog.model.Address;
import newLog.newLog.repository.AddressRepository;
import newLog.newLog.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

import static newLog.newLog.model.Milestone_.address;


@RestController
@RequestMapping("/api/addresses")
public class AddressController {



    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressMapper addressMapper;



    @GetMapping
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return addressMapper.addressesToDtos(addresses);
    }


    @PostMapping
    public AddressDto createNewAddress(@RequestBody @Valid AddressDto addressDto) {
        if (addressDto.getId() != null) {
            throw new IllegalArgumentException("ID must be zero for a new address");
        }
        try {
            Address savedAddress = addressService.saveAddress(addressMapper.dtoToAddress(addressDto));
            return addressMapper.addressToDto(savedAddress);
        } catch (InvalidAddressException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid address data", e);
        }
    }
    @DeleteMapping("/{id}")
    public AddressDto deleteAddressId(@PathVariable Long id) {
        return addressMapper.addressToDto(addressService.delete(id));

    }
    @GetMapping("/{id}")
    public AddressDto getAddressById(@PathVariable Long id) {
        Address requestedAddress = addressService.getAddressId(id);
        if(address == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return addressMapper.addressToDto(requestedAddress);
    }

    @PutMapping("/{id}")
    public AddressDto updateId(@PathVariable Long id, @RequestBody @Valid AddressDto addressDto) {
        if (addressDto.getId() != null && !Objects.equals(addressDto.getId(), id)) {
            throw new IdMismatchException("The id from path (" + id + ") and from the body (" + addressDto.getId() + ") is mismatched");
        }
        try {
            Address updatingAddress = addressMapper.dtoToAddress(addressDto);
            updatingAddress.setId(id);
            Address updateId = addressService.updateId(updatingAddress);
            return addressMapper.addressToDto(updateId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found with id: " + id, e);
        }

    }/*
        @PostMapping("/search")
        public ResponseEntity<List<AddressDto>> addressSearch(
                @RequestBody AddressSearchDto exampleDto,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", required = false) Integer size,
        @RequestParam(value = "sort", defaultValue = "id,asc") String sort) {

            RequestValidator.validateSearchDto(exampleDto);


            if (size == null) {
                size = Integer.MAX_VALUE;
            }


            String[] sortParams = sort.split(",");
            String sortField = sortParams[0];
            Sort.Direction sortDirection = sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1])
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;


            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));


            Page<Address> resultPage = addressService.findAddressesByExample(exampleDto, pageable);


            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("X-Total-Count", String.valueOf(resultPage.getTotalElements()));
            return new ResponseEntity<>(resultPage.map(addressMapper::addressToDto).getContent(), responseHeaders, HttpStatus.OK);
        }*/
    @PostMapping(value = "/search")
    public ResponseEntity<List<AddressDto>> searchByExample(@RequestBody AddressSearchDto searchDto,
                                                            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(value = "size", required = false) Integer size,
                                                            @RequestParam(value = "sort", required = false, defaultValue = "id,asc") String sort) {

        if (searchDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (size == null) {
            size = Integer.MAX_VALUE;
        }


        String sortBy;
        String[] sortParam = sort.split(",");
        Sort order;

        if (!"".equals(sortParam[0])) {
            sortBy = sortParam[0];
            if ("desc".equals(sortParam[1])) {
                order = Sort.by(sortBy).descending();
            } else {
                order = Sort.by(sortBy).ascending();
            }
        } else {
            if ("desc".equals(sortParam[1])) {
                order = Sort.by("id").descending();
            } else {
                order = Sort.by("id").ascending();
            }
        }

        Pageable pageable = PageRequest.of(page, size).withSort(order);
        Page<Address> pages = addressService.findAddressesByExample(searchDto, pageable);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(pages.getTotalElements()))
                .body(addressMapper.MAPPER.addressesToDtos(pages.getContent()));
    }

    }








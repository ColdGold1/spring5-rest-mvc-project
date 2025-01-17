package guru.springfamework.service;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.category.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class VendorServiceImplTest {


    private static final Long ID = 1L;
    private static final String NAME = "Name";
    private static final String VENDOR_URL = VendorController.BASE_URL + "/" + ID;
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }


    @Test
    public void getVendorById() {

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        Optional<Vendor> vendorOptional = Optional.of(vendor);

        when(vendorRepository.findById(anyLong())).thenReturn(vendorOptional);


        VendorDTO vendorDTO = vendorService.getVendorById(ID);

        assertNotNull(vendorDTO);
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void getAllVendors() {
        List<Vendor> vendorList = List.of(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendorList);

        VendorListDTO vendorListDTO = vendorService.getAllVendors();

        assertEquals(vendorList.size(), vendorListDTO.getVendors().size());

    }

    @Test
    public void createNewVendor() {

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO vendorDTO = vendorService.createNewVendor(VendorMapper.INSTANCE.vendorToVendorDTO(vendor));

        assertNotNull(vendorDTO);
        assertEquals(NAME, vendorDTO.getName());
        assertEquals(VENDOR_URL, vendorDTO.getVendorUrl());
    }

    @Test
    public void saveVendorByDTO() {

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        VendorDTO savedDTO = vendorService.saveVendorByDTO(ID, vendorDTO);

        assertNotNull(savedDTO);
        assertEquals(NAME, savedDTO.getName());
        assertEquals(VENDOR_URL, savedDTO.getVendorUrl());
    }

    @Test
    public void patchVendor() {

        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToVendorDTO(vendor);
        vendorDTO.setName(NAME + "123");

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        VendorDTO returnDTO = vendorService.patchVendor(ID,vendorDTO);

        assertNotNull(returnDTO);
        assertEquals(NAME + "123", returnDTO.getName());
        assertEquals(VENDOR_URL, returnDTO.getVendorUrl());

    }

    @Test
    public void deleteVendorById() {

        vendorRepository.deleteById(ID);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}
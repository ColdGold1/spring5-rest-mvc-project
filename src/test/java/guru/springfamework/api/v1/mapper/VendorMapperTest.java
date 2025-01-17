package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;
    private static final String NAME = "Name";

    @Test
    public void vendorToVendorDTO() {

        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertNotNull(vendorDTO);
        assertEquals(NAME, vendorDTO.getName());
    }

    @Test
    public void vendorDTOToVendor() {

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertNotNull(vendor);
        assertEquals(NAME, vendor.getName());

    }
}
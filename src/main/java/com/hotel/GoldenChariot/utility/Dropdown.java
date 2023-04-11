package com.hotel.GoldenChariot.utility;

import com.hotel.GoldenChariot.dto.DropdownDto;

import java.util.LinkedList;
import java.util.List;

public class Dropdown {
    public static List<DropdownDto> dropdownRole(){
        List<DropdownDto> result = new LinkedList<>();
        result.add(new DropdownDto("Super Admin", "Super Admin"));
        result.add(new DropdownDto("Admin", "Admin"));
        result.add(new DropdownDto("Customer", "Customer"));
        return result;
    }

    public static List<DropdownDto> dropdownType(){
        List<DropdownDto> result = new LinkedList<>();
        result.add(new DropdownDto("Single", "Single"));
        result.add(new DropdownDto("Double", "Double"));
        result.add(new DropdownDto("Tripple", "Tripple"));
        return result;
    }
}

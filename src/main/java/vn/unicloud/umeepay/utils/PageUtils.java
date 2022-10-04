package vn.unicloud.umeepay.utils;

import org.springframework.data.domain.*;
import vn.unicloud.umeepay.dtos.request.PaginationAndSortingRequest;
import java.util.List;

public class PageUtils {
    public static Pageable createPageable(int page, int size, String sort, String sortColumn) {
        Sort sortable = Sort.by(sortColumn).descending();
        if (sort.trim().equalsIgnoreCase("asc"))
            sortable = Sort.by(sortColumn).ascending();
        return PageRequest.of(page, size, sortable);
    }

    public static Pageable createPageable(PaginationAndSortingRequest request) {
        Sort sortable = Sort.by(request.getSortBy()).descending();
        if (request.getSortDir().trim().equalsIgnoreCase("asc"))
            sortable = Sort.by(request.getSortBy()).ascending();
        return PageRequest.of(request.getPage(), request.getSize(), sortable);
    }


    public static <T> Page<T> convertListToPage(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }
}

package edu.com.softserveinc.bawl.dto.comparators;

import edu.com.softserveinc.bawl.dto.UserHistoryDto;

import java.util.Comparator;

/**
 * Created by Oleg on 13.10.2015.
 */
public class UserHistoryDtoComparator implements Comparator<UserHistoryDto> {
    @Override
    public int compare(UserHistoryDto historyDto1, UserHistoryDto historyDto12) {
        return historyDto1.getDate().compareTo(historyDto12.getDate());
    }
}

package com.HatshbirSingh_COMP303_Assignment3.services;

import com.HatshbirSingh_COMP303_Assignment3.model.Seeker;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SeekerService {
    private Map<Integer, Seeker> seekers = new HashMap<>();
    private int idCounter = 1;

    public List<Seeker> getAllSeekers() {
        return seekers.values().stream().collect(Collectors.toList());
    }

    public Seeker getSeekerById(Integer id) {
        return seekers.get(id);
    }

    public Seeker addSeeker(Seeker seeker) {
        seeker.setId(idCounter++);
        seekers.put(seeker.getId(), seeker);
        return seeker;
    }

    public Seeker updateSeeker(Integer id, Seeker seeker) {
        seeker.setId(id);
        seekers.put(id, seeker);
        return seeker;
    }

    public void deleteSeeker(Integer id) {
        seekers.remove(id);
    }
}

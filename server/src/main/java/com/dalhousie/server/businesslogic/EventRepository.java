package com.dalhousie.server.businesslogic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.dalhousie.server.model.Event;

@Component
public class EventRepository implements IEventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Event> findAll() {
        return jdbcTemplate.query("SELECT * FROM events", BeanPropertyRowMapper.newInstance(Event.class));
    }

    @Override
    public int save(Event event) {
        return jdbcTemplate.update(
                "INSERT INTO events(id, title, description, event_type, status, start_datetime, end_datetime, venue, max_capacity, updated_at, created_at) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                null, event.getTitle(), event.getDescription(), event.getEventType().toString(), event.getStatus(),
                event.getStartDateTime(), event.getEndDateTime(), event.getVenue(), event.getMaxCapacity(),
                event.getUpdateAt(), event.getCreatedAt());
    }

    @Override
    public int update(Event event) {
        return jdbcTemplate.update(
                "UPDATE events SET title=?, description=?, event_type=?, status=?, start_datetime=?, end_datetime=?, venue=?, max_capacity=?, updated_at=?, created_at=? WHERE id=?",
                event.getTitle(), event.getDescription(), event.getEventType().toString(), event.getStatus(),
                event.getStartDateTime(), event.getEndDateTime(), event.getVenue(), event.getMaxCapacity(),
                event.getUpdateAt(), event.getCreatedAt(), event.getId());
    }

    @Override
    public int delete(Event event) {
        return jdbcTemplate.update("DELETE FROM events WHERE id=?", event.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM events WHERE id=?", id);
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }

    @Override
    public Optional<Event> getById(Integer id) {
        try {
            Event event = jdbcTemplate.queryForObject("SELECT * from events WHERE id=?", BeanPropertyRowMapper.newInstance(Event.class), id);
            return Optional.of(event);
        }catch(Exception e) {
            return Optional.empty();
        }
    }

}

package ru.coldsun.homework8.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.coldsun.homework8.aspect.TrackUserAction;
import ru.coldsun.homework8.model.Note;
import ru.coldsun.homework8.repository.NoteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    @TrackUserAction
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    @TrackUserAction
    public Note getByIdNotes(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }
    @TrackUserAction
    public void deleteByNotes(Long id) {
        noteRepository.deleteById(id);
    }
    @TrackUserAction
    public Note addNotes(Note note) {
        note.setCreationDate(LocalDateTime.now());
        return  noteRepository.save(note);
    }
    @TrackUserAction
    public Note updateNoteStatus(Long id, Note note) {
        Note changeNote = noteRepository.findById(id).orElse(null);
        if (changeNote != null) {
            if (note.getContent()!=null) changeNote.setContent(note.getContent());
            if (note.getContent()!=null) changeNote.setHeading(note.getContent());
            if (note.getCreationDate()!=null) changeNote.setCreationDate(note.getCreationDate());
            return noteRepository.save(changeNote);
        } else {
            return null;
        }
    }
}

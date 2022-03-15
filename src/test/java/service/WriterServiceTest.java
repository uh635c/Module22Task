package service;

import com.uh635c.task22.model.Writer;
import com.uh635c.task22.repository.WriterRepository;
import com.uh635c.task22.service.WriterService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

public class WriterServiceTest {
    private static WriterService writerService;
    private static WriterRepository writerRepositoryMock;

    @BeforeClass
    public static void getWriterService(){
        writerRepositoryMock = Mockito.mock(WriterRepository.class);
        writerService = new WriterService(writerRepositoryMock);
    }

    @Test
    public void shouldReturnListOfWriters(){
        List<Writer> writersActual = new ArrayList<>();
        writersActual.add(new Writer(1L, "writer1", null));
        writersActual.add(new Writer(2L, "writer2", null));

        List<Writer> writersExpected = new ArrayList<>();
        writersExpected.add(new Writer(1L, "writer1", null));
        writersExpected.add(new Writer(2L, "writer2", null));

        Mockito.when(writerRepositoryMock.getAll()).thenReturn(writersActual);

        Assert.assertEquals(writersExpected, writerService.getAll());
    }

    @Test
    public void shouldReturnWriterById(){
        Writer writerActual = new Writer(1L, "writer1", null);
        Writer writerExpected = new Writer(1L, "writer1", null);

        Mockito.when(writerRepositoryMock.getById(Mockito.any())).thenReturn(writerActual);

        Assert.assertEquals(writerExpected, writerService.getById(1L));
    }

    @Test
    public void shouldReturnSavedWriter(){
        Writer writerActual = new Writer(1L, "writer1", null);
        Writer writerExpected = new Writer(1L, "writer1", null);

        Mockito.when(writerRepositoryMock.save(writerActual)).thenReturn(writerActual);

        Assert.assertEquals(writerExpected, writerService.save(writerActual));
    }

    @Test
    public void shouldReturnUpdatedWriter(){
        Writer writerActual = new Writer(1L, "writer1", null);
        Writer writerExpected = new Writer(1L, "writer1", null);

        Mockito.when(writerService.update(writerActual)).thenReturn(writerActual);

        Assert.assertEquals(writerExpected, writerService.update(writerActual));
    }

    @Test
    public void shouldCallWriterRepositoryRemoveMethod(){
        writerService.remove(1L);

        Mockito.verify(writerRepositoryMock).remove(1L);
    }
}

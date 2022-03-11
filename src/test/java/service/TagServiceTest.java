package service;

import com.uh635c.task22.model.Tag;
import com.uh635c.task22.repository.TagRepository;
import com.uh635c.task22.service.TagService;
import org.junit.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;

public class TagServiceTest {
    private static TagService tagService;
    private static TagRepository tagRepositoryMock;

    @BeforeClass
    public static void getTagService(){
        tagRepositoryMock = Mockito.mock(TagRepository.class);
        tagService = new TagService(tagRepositoryMock);
    }

    @Test
    public void shouldReturnListOfTags(){
        List<Tag> tagsActual = new ArrayList<>();
        tagsActual.add(new Tag(1L, "tag1"));
        tagsActual.add(new Tag(2L, "tag2"));
        List<Tag> tagsExpected = new ArrayList<>();
        tagsExpected.add(new Tag(1L, "tag1"));
        tagsExpected.add(new Tag(2L, "tag2"));

        Mockito.when(tagRepositoryMock.getAll()).thenReturn(tagsActual);

        Assert.assertEquals(tagsExpected, tagService.getAll());
    }

    @Test
    public void shouldReturnTagById(){
        Tag tagActual = new Tag(1L, "tag1");
        Tag tagExpected = new Tag(1L, "tag1");

        Mockito.when(tagRepositoryMock.getById(any())).thenReturn(tagActual);

        Assert.assertEquals(tagExpected, tagService.getById(1L));
    }

    @Test
    public void shouldReturnSavedTag(){
        Tag tagActual = new Tag(3L, "tag3");
        Tag tagExpected = new Tag(3L, "tag3");

        Mockito.when(tagRepositoryMock.save(tagActual)).thenReturn(tagActual);

        Assert.assertEquals(tagExpected, tagService.save(tagActual));
    }

    @Test
    public void shouldReturnUpdatedTag(){
        Tag tagActual = new Tag(3L, "tag3");
        Tag tagExpected = new Tag(3L, "tag3");

        Mockito.when(tagRepositoryMock.update(tagActual)).thenReturn(tagActual);

        Assert.assertEquals(tagExpected, tagService.update(tagActual));
    }

    @Test
    public void shouldCallTagRepositoryMethod(){
        tagService.remove(1L);

        Mockito.verify(tagRepositoryMock).remove(1L);
    }
}

package pl.gov.coi.blox.api.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gov.coi.blox.entity.BlogDto;
import pl.gov.coi.blox.entity.BlogViewDto;
import pl.gov.coi.blox.service.BlogService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogRestController {

    private final BlogService blogService;

    @PostMapping("/")
    public void createBlog(@RequestBody BlogDto blogDto) {
        blogService.createBlog(blogDto);
    }

    @GetMapping("/{id}")
    public BlogViewDto getBlog(@PathVariable("id")Long id) { return blogService.getBlogById(id);
    }
}

package fpoly.java6.assignment.service;

import fpoly.java6.assignment.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private HttpSession session;

    public void addToCart(Item item) {
        List<Item> items =  Optional.ofNullable((List<Item>)session.getAttribute("items"))
                                    .orElseGet(ArrayList::new);
        items.add(item);
        session.setAttribute("items", items);
    }

    public void reAdd(List<Item> items) {
        session.setAttribute("items", items);
    }

    public List<Item> getAll() {
        List<Item> items = (List<Item>) session.getAttribute("items");
        if (items == null) {
            session.setAttribute("items", new ArrayList<>());
        }
        return items;
    }

    public void removeAll() {
        session.removeAttribute("items");
    }
}

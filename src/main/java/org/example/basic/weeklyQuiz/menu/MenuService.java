package org.example.basic.weeklyQuiz.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuService {

    private MenuRepository menuRepository;
    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional(readOnly = true)
    public List<MenuDTO> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(MenuDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<MenuDTO> getMenuById(Long id) {
        return menuRepository.findById(id)
                .map(MenuDTO::toDTO);
    }

    public void makeMenu(MenuDTO menuDTO) {
        menuRepository.save(MenuDTO.toMenu(menuDTO));
    }

    public MenuDTO updateMenu(Long id, MenuDTO menuDTO) {
        Menu findMenu = menuRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
        findMenu.updateMenu(menuDTO);
        return MenuDTO.toDTO(findMenu);
    }

    public boolean deleteMenu(Long id) {
        return menuRepository.findById(id)
                .map(menu -> {menuRepository.delete(menu); return true;})
                .orElse(false);
    }
}

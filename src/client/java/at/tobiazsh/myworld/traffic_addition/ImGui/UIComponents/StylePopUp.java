package at.tobiazsh.myworld.traffic_addition.ImGui.UIComponents;

import at.tobiazsh.myworld.traffic_addition.ImGui.ImGuiImpl;
import at.tobiazsh.myworld.traffic_addition.ImGui.Utilities.FileSystem;
import at.tobiazsh.myworld.traffic_addition.components.BlockEntities.CustomizableSignBlockEntity;
import imgui.ImGui;

import java.util.Objects;

import static at.tobiazsh.myworld.traffic_addition.ImGui.Screens.SignEditorScreen.previewTextures;
import static at.tobiazsh.myworld.traffic_addition.ImGui.Screens.SignEditorScreen.signJson;
import static at.tobiazsh.myworld.traffic_addition.Utils.SignStyleJson.deconstructStyleToArray;

public class StylePopUp {

    private static boolean shouldOpen = false;
    private static boolean applyButtonDisabled = true;
    private static boolean styleSelected = false;
    private static FileSystem.Folder currentBGStyle = new FileSystem.Folder("No Style Selected", "/ImGui/SignRes/Backgrounds/Austria/Normal"); // Default to Austria's Road Style
    private static FileSystem.Folder oldBGStyle = null;
    private static FileSystem.Folder currentCountryBG = new FileSystem.Folder("No Country Selected", "/");
    private static final ImGui imgui = new ImGui();
    private static FileSystem.Folder availableBGStyles = new FileSystem.Folder(null, null);

    public static void render(FileSystem.Folder countriesBG, CustomizableSignBlockEntity customizableSignBlockEntity) {
        ImGui.setNextWindowSize(1000, 750);
        if (ImGui.beginPopupModal("Choose Style Type")) {
            ImGui.pushFont(ImGuiImpl.DejaVuSansBoldBig);
            ImGui.setCursorPosX((1000 - imgui.calcTextSize("Styling Settings").x) / 2);
            ImGui.text("Styling Settings");
            ImGui.popFont();

            ImGui.separator();

            ImGui.pushFont(ImGuiImpl.DejaVuSansBold);
            ImGui.text("Country");
            ImGui.popFont();

            if (ImGui.beginCombo("##country", currentCountryBG.name)) {
                countriesBG.forEach(country -> {
                    boolean isSelected = (Objects.equals(currentCountryBG.name, country.name));

                    // If country is selected, search the country's folder for styles, which are also folders, and put them in a list
                    if (ImGui.selectable(country.name, isSelected)) {
                        currentCountryBG = (FileSystem.Folder) country;

                        try {
                            availableBGStyles = FileSystem.FromResource.listFolders(country.path);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (isSelected) ImGui.setItemDefaultFocus();
                });

                ImGui.endCombo();
            }

            ImGui.spacing();

            ImGui.pushFont(ImGuiImpl.DejaVuSansBold);
            ImGui.text("Style");
            ImGui.popFont();

            if (ImGui.beginCombo("##style", currentBGStyle.name)) {

                availableBGStyles.forEach(style -> {
                    boolean isSelected = (Objects.equals(currentBGStyle.name, style.name));
                    if (ImGui.selectable(style.name, isSelected)) {
                        oldBGStyle = currentBGStyle;
                        currentBGStyle = (FileSystem.Folder) style;
                        styleSelected = true;
                    }
                });

                ImGui.endCombo();
            }

            ImGui.spacing();

            if (ImGui.button("Cancel")) {
                styleSelected = false;
                currentBGStyle = oldBGStyle;
                ImGui.closeCurrentPopup();
            }

            ImGui.sameLine();

            applyButtonDisabled = !styleSelected;

            if (applyButtonDisabled) ImGui.beginDisabled();

            if (ImGui.button("Apply")) {
                styleSelected = false;
                ImGui.closeCurrentPopup();

                previewTextures = deconstructStyleToArray(signJson.setStyle(currentBGStyle.path, customizableSignBlockEntity));
            }

            if (applyButtonDisabled) ImGui.endDisabled();

            ImGui.endPopup();
        }

        if (shouldOpen) {
            ImGui.openPopup("Choose Style Type");
            shouldOpen = false;
        }
    }

    public static void open() {
        shouldOpen = true;
    }
}

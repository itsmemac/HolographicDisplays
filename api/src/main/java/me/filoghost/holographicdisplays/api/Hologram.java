/*
 * Copyright (C) filoghost and contributors
 *
 * SPDX-License-Identifier: GPL-3.0-or-later
 */
package me.filoghost.holographicdisplays.api;

import me.filoghost.holographicdisplays.api.line.HologramLine;
import me.filoghost.holographicdisplays.api.line.ItemLine;
import me.filoghost.holographicdisplays.api.line.TextLine;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An object made of various lines, that can be items or holograms.
 * Holographic lines appear as a nametag without any entity below.
 * To create one, please see {@link HolographicDisplaysAPI#createHologram(Location)}.
 *
 * @since 1
 */
public interface Hologram {
    
    /**
     * Appends a text line to end of this hologram.
     *
     * @param text the content of the line, can be null for an empty line
     * @return the new TextLine appended
     * @since 1
     */
    @NotNull TextLine appendTextLine(@Nullable String text);
    
    
    /**
     * Appends an item line to end of this hologram.
     *
     * @param itemStack the content of the line
     * @return the new ItemLine appended
     * @since 1
     */
    @NotNull ItemLine appendItemLine(@NotNull ItemStack itemStack);
    
    
    /**
     * Inserts a text line in this hologram.
     *
     * @param index the line is inserted before this index. If 0, the new line will be inserted before the first line.
     * @param text the content of the line, can be null for an empty line
     * @return the new TextLine inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
     * @since 1
     */
    @NotNull TextLine insertTextLine(int index, @Nullable String text);
    
    
    /**
     * Inserts an item line in this hologram.
     *
     * @param index the line is inserted before this index. If 0, the new line will be inserted before the first line.
     * @param itemStack the content of the line
     * @return the new ItemLine inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
     * @since 1
     */
    @NotNull ItemLine insertItemLine(int index, @NotNull ItemStack itemStack);
    
    
    /**
     * Finds the element at a given index in the lines.
     *
     * @param index the index of the line to retrieve.
     * @return the hologram line at the given index, can be an {@link ItemLine} or a {@link TextLine}.
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
     * @since 1
     */
    @NotNull HologramLine getLine(int index);
    
    /**
     * Removes a line at a given index. Since: v2.0.1
     *
     * @param index the index of the line, that should be between 0 and size() - 1.
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size())
     * @since 1
     */
    void removeLine(int index);
    
    
    /**
     * Removes all the lines from this hologram.
     *
     * @since 1
     */
    void clearLines();

    
    /**
     * Checks the amount of lines of the hologram.
     *
     * @return the amount of lines
     * @since 1
     */
    int getLineCount();
    
    
    /**
     * The physical height of the hologram, counting all the lines. Since: v2.1.4
     *
     * @return the height of the hologram, counting all the lines and the gaps between them
     * @since 1
     */
    double getHeight();
    
    
    /**
     * Teleports a hologram to the given location.
     *
     * @param location the new location
     * @since 1
     */
    void teleport(@NotNull Location location);
    
    
    /**
     * Teleports a hologram to the given location.
     *
     * @param world the world where the hologram should be teleported, use {@link #getWorld()} to teleport it in the same world.
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param z the Z coordinate
     * @since 1
     */
    void teleport(@NotNull World world, double x, double y, double z);
    
    /**
     * Returns the position of the hologram.
     *
     * @return the Location of the hologram
     * @since 1
     */
    @NotNull Location getLocation();
    
    /**
     * Returns the X coordinate.
     *
     * @return the X coordinate of the hologram
     * @since 1
     */
    double getX();
    
    
    /**
     * Returns the Y coordinate.
     *
     * @return the Y coordinate of the hologram
     * @since 1
     */
    double getY();
    
    
    /**
     * Returns the Z coordinate.
     *
     * @return the Z coordinate of the hologram
     * @since 1
     */
    double getZ();
    
    
    /**
     * Returns the world.
     *
     * @return the world of the hologram
     * @since 1
     */
    @NotNull World getWorld();
    
    
    /**
     * Returns the {@link VisibilitySettings} of this hologram.
     * <br><b style = "color: red">Note</b>: the usage of the VisibilitySettings requires ProtocolLib.
     * Without the plugin, holograms will be always visible.
     *
     * @return the VisibilitySettings of this hologram
     * @since 1
     */
    @NotNull VisibilitySettings getVisibilitySettings();
    
    
    /**
     * Returns when the hologram was created. Useful for removing old holograms.
     *
     * @return the timestamp of when the hologram was created, in milliseconds
     * @since 1
     */
    long getCreationTimestamp();

    /**
     * Checks if the hologram will track and replace placeholders.
     * This is false by default.
     *
     * @return if the hologram allows placeholders
     * @since 1
     */
    boolean isAllowPlaceholders();

    /**
     * Sets if the hologram should track and replace placeholders.
     * By default if will not track them.
     *
     * @param allowPlaceholders if the hologram should track placeholders
     * @since 1
     */
    void setAllowPlaceholders(boolean allowPlaceholders);
    
    /**
     * Deletes this hologram. Editing or teleporting the hologram when deleted
     * will throw an exception. Lines will be automatically cleared.
     * You should remove all the references of the hologram after deletion.
     *
     * @since 1
     */
    void delete();
    
    
    /**
     * Checks if a hologram was deleted.
     *
     * @return true if this hologram was deleted
     * @since 1
     */
    boolean isDeleted();
    
}
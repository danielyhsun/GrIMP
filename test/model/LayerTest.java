package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for layer class.
 */
public class LayerTest {

  @Test
  public void testLayerCreation() {
    Layer layer = new Layer("layer", 500, 500);
    assertNotNull(layer);
    assertEquals("layer", layer.getName());
    assertEquals("Normal", layer.getFilterName());

    Layer bg = new Layer(500, 500);
    assertNotNull(bg);
    assertEquals("bg", bg.getName());
    assertEquals("Normal", bg.getFilterName());
  }

  @Test
  public void testSetFilter() {
    Layer layer = new Layer("layer", 500, 500);
    assertEquals("Normal", layer.getFilterName());
    layer.setFilter("brighten-value");
    assertEquals("brighten-value", layer.getFilterName());
  }

  @Test (expected = IllegalArgumentException.class)
  public void addInvalidFilter() {
    Layer layer = new Layer("layer", 500, 500);
    layer.setFilter("invalid-filter");
    layer.addFilter(layer.getFilterName());
  }

}

package uz.shox.netnomer

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CarrierPageConfigTest {
    @Test
    fun carrierConfigsContainAllOperatorsWithExpectedActions() {
        assertEquals(4, CarrierPageConfigs.all.size)

        val uzmobile = CarrierPageConfigs.require(CarrierId.Uzmobile)
        assertEquals(Constants.Links.UZMOBILE_WEBSITE, uzmobile.websiteUrl)
        assertTrue(uzmobile.appAction is CarrierAppAction.OpenUrl)

        val ucell = CarrierPageConfigs.require(CarrierId.Ucell)
        assertEquals(Constants.Links.UCELL_WEBSITE, ucell.websiteUrl)
        assertTrue(ucell.appAction is CarrierAppAction.OpenUrl)

        val beeline = CarrierPageConfigs.require(CarrierId.Beeline)
        assertEquals(Constants.Links.BEELINE_WEBSITE, beeline.websiteUrl)
        assertTrue(beeline.appAction is CarrierAppAction.OpenUrl)

        val mobiuz = CarrierPageConfigs.require(CarrierId.Mobiuz)
        assertEquals(Constants.Links.MOBIUZ_WEBSITE, mobiuz.websiteUrl)
        assertTrue(mobiuz.appAction is CarrierAppAction.OpenUrl)
    }
}

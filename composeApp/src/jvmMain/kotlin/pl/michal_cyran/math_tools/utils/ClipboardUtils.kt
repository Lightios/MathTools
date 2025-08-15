package pl.michal_cyran.math_tools.utils

import java.awt.Toolkit
import java.awt.datatransfer.*
import java.awt.image.BufferedImage

class TransferableImage(private val image: BufferedImage) : Transferable {
    override fun getTransferDataFlavors(): Array<DataFlavor> =
        arrayOf(DataFlavor.imageFlavor)

    override fun isDataFlavorSupported(flavor: DataFlavor): Boolean =
        flavor == DataFlavor.imageFlavor

    override fun getTransferData(flavor: DataFlavor): Any =
        if (flavor == DataFlavor.imageFlavor) image else throw UnsupportedFlavorException(flavor)
}

fun copyImageToClipboard(image: BufferedImage) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    val transferable = TransferableImage(image)
    clipboard.setContents(transferable, null)
}
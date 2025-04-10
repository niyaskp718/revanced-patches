package app.revanced.patches.youtube.layout.hidecrowdfundingbox.bytecode.fingerprints

import app.revanced.patcher.annotation.Name
import app.revanced.patcher.annotation.Version
import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint
import app.revanced.patches.youtube.layout.hidecrowdfundingbox.annotations.CrowdfundingBoxCompatibility
import app.revanced.patches.youtube.layout.hidecrowdfundingbox.resource.patch.CrowdfundingBoxResourcePatch
import org.jf.dexlib2.Opcode
import org.jf.dexlib2.iface.instruction.WideLiteralInstruction

@Name("crowdfunding-box-view-parent-fingerprint")
@CrowdfundingBoxCompatibility
@Version("0.0.1")
object CrowdfundingBoxFingerprint : MethodFingerprint(
    opcodes = listOf(
        Opcode.CONST_4,
        Opcode.CONST,
        Opcode.INVOKE_VIRTUAL,
        Opcode.MOVE_RESULT_OBJECT,
        Opcode.IPUT_OBJECT,
        Opcode.CONST,
    ),
    customFingerprint = { methodDef ->
        methodDef.implementation?.instructions?.any { instruction ->
            instruction.opcode.ordinal == Opcode.CONST.ordinal &&
                    (instruction as? WideLiteralInstruction)?.wideLiteral == CrowdfundingBoxResourcePatch.crowdfundingBoxId
        } == true
    }
)
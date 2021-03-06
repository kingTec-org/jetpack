package androidx.compose.plugins.kotlin.analysis

import androidx.compose.plugins.kotlin.ComposableAnnotationChecker
import androidx.compose.plugins.kotlin.ComposerMetadata
import androidx.compose.plugins.kotlin.ResolvedRestartCalls
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.FunctionDescriptor
import org.jetbrains.kotlin.descriptors.ParameterDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.descriptors.VariableDescriptor
import org.jetbrains.kotlin.psi.Call
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtReferenceExpression
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.util.slicedMap.BasicWritableSlice
import org.jetbrains.kotlin.util.slicedMap.RewritePolicy
import org.jetbrains.kotlin.util.slicedMap.WritableSlice
import org.jetbrains.kotlin.types.KotlinType

object ComposeWritableSlices {
    val COMPOSABLE_ANALYSIS: WritableSlice<KtElement, ComposableAnnotationChecker.Composability> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
    val FCS_CALL_WITHIN_COMPOSABLE: WritableSlice<KtElement, Boolean> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
    val FCS_RESOLVEDCALL_COMPOSABLE: WritableSlice<KtElement, Boolean> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
    val INFERRED_COMPOSABLE_DESCRIPTOR: WritableSlice<FunctionDescriptor, Boolean> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
    val STABLE_TYPE: WritableSlice<KotlinType, Boolean?> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
    val RESTART_CALLS_NEEDED: WritableSlice<SimpleFunctionDescriptor, Boolean> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
    val RESTART_CALLS: WritableSlice<SimpleFunctionDescriptor, ResolvedRestartCalls> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
    val COMPOSER_METADATA: WritableSlice<VariableDescriptor, ComposerMetadata> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
    val IGNORE_COMPOSABLE_INTERCEPTION: WritableSlice<Call, Boolean> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
    val IS_EMIT_CHILDREN_PARAMETER: WritableSlice<ParameterDescriptor, Boolean> =
        BasicWritableSlice(RewritePolicy.DO_NOTHING)
}

private val REWRITES_ALLOWED = object : RewritePolicy {
    override fun <K : Any?> rewriteProcessingNeeded(key: K): Boolean = true
    override fun <K : Any?, V : Any?> processRewrite(
        slice: WritableSlice<K, V>?,
        key: K,
        oldValue: V,
        newValue: V
    ): Boolean = true
}

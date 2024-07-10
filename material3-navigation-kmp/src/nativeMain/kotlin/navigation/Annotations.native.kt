package navigation

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
actual annotation class NavigatorName actual constructor(actual val value: String)
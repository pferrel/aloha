package com.eharmony.aloha.feature

trait Indicator { self: DefaultPossessor =>
    @inline def ind(a: AnyVal): Iterable[(String, Double)] = h(a)
    @inline def ind[A >: AnyVal with String](a: Option[A]): Iterable[(String, Double)] = a.map(h) getOrElse DefaultForMissingDataInReg
    @inline def ind(a: String): Iterable[(String, Double)] = h(a)
    @inline def ind(a: Iterable[String])(implicit d: DummyImplicit): Iterable[(String, Double)] = a.map(b => ((s"=$b", 1.0)))
    @inline def ind(a: Iterable[Option[String]]): Iterable[(String, Double)] = a.flatten.map(b => ((s"=$b", 1.0)))

    /** Indicator for any Enum class.
      * @param e a java enum constant
      * @tparam E The enum type
      * @return an indicator.
      */
    @inline def ind[E <: Enum[E]](e: E) = h(e.name)

    /** Don't want to expose this directly.  Any is too broad.
      * @param a
      * @return
      */
    @inline private[this] def h(a: Any) = Iterable((s"=$a", 1.0))
}

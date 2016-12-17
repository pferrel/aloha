package com.eharmony.aloha.score.audit.take3

import com.eharmony.aloha.id.ModelIdentity
import com.eharmony.aloha.reflect.RefInfo
import com.eharmony.aloha.score.audit.take3.TypeCtor.Aux

/**
  * Created by ryan on 12/16/16.
  */
case class OptionAuditor[A](implicit val refInfo: RefInfo[A]) extends TypedAuditor[ModelIdentity, TypeCtor.Aux[Option], A, Option[A]]{
  override private[aloha] def failure[S](tc: Aux[Option], key: ModelIdentity, errorMsgs: => Seq[String], missingVarNames: => Set[String], subValues: Seq[Option[S]]): Option[A] = None
  override private[aloha] def success[S](tc: Aux[Option], key: ModelIdentity, valueToAudit: A, missingVarNames: => Set[String], subValues: Seq[Option[S]], prob: => Option[Double]): Option[A] = Option(valueToAudit)
}

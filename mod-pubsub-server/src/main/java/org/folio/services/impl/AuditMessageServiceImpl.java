package org.folio.services.impl;

import io.vertx.core.Future;
import org.folio.dao.AuditMessageDao;
import org.folio.rest.jaxrs.model.AuditMessageCollection;
import org.folio.rest.jaxrs.model.AuditMessagePayload;
import org.folio.rest.util.AuditMessageFilter;
import org.folio.services.AuditMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditMessageServiceImpl implements AuditMessageService {

  private AuditMessageDao auditMessageDao;

  public AuditMessageServiceImpl(@Autowired AuditMessageDao auditMessageDao) {
    this.auditMessageDao = auditMessageDao;
  }

  @Override
  public Future<AuditMessageCollection> getAuditMessages(AuditMessageFilter auditMessageFilter, String tenantId) {
    return auditMessageDao.getAuditMessages(auditMessageFilter, tenantId)
      .map(auditMessages -> new AuditMessageCollection()
        .withAuditMessages(auditMessages)
        .withTotalRecords(auditMessages.size()));
  }

  @Override
  public Future<Optional<AuditMessagePayload>> getAuditMessagePayloadByEventId(String eventId, String tenantId) {
    return auditMessageDao.getAuditMessagePayloadByEventId(eventId, tenantId);
  }
}

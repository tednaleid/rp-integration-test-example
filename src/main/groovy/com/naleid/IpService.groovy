package com.naleid

import rx.Observable

import javax.inject.Inject

class IpService {
    private final CollaboratorService collaboratorService

    @Inject
    public IpService(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService
    }

    public Observable<String> lookupResult() {
        return Observable.create({ subscriber ->
            new Thread({ ->
                subscriber.onNext(collaboratorService.findValue())
                subscriber.onCompleted()
            }).start()
        })
    }
}
